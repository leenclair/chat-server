package com.example.chatserver.security.handler;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.chatroom.ChatRoom;
import com.example.chatserver.domain.chatroom.ChatRoomInfo;
import com.example.chatserver.domain.chatroom.ChatRoomReader;
import com.example.chatserver.domain.chatroom.ChatRoomService;
import com.example.chatserver.domain.member.MemberService;
import com.example.chatserver.infrastructure.chatroom.ChatRoomRepository;
import com.example.chatserver.infrastructure.member.MemberRepository;
import com.example.chatserver.security.JwtProvider;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String DESTINATION_DELIMITER = "/";
    private static final int TOKEN_START_INDEX = 7;
    private static final int ROOM_ID_INDEX = 2;

    private final JwtProvider jwtProvider;
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        final StompCommand command = accessor.getCommand();

        try {
            if (StompCommand.CONNECT.equals(command)) {
                validateToken(accessor);
                log.info("Token validation completed successfully");
            }

            if (StompCommand.SUBSCRIBE.equals(command)) {
                String bearerToken = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER);
                if (bearerToken == null || !bearerToken.startsWith(BEARER_PREFIX)) {
                    throw new BadCredentialsException("Missing or invalid Authorization header");
                }

                String token = bearerToken.substring(TOKEN_START_INDEX);
                if(jwtProvider.validateToken(token)){
                    String email = jwtProvider.getEmailFromToken(token);
                    validateRoomAccess(email, accessor);
                }else {
                    log.error("Invalid token in SUBSCRIBE request");
                    throw new BadCredentialsException("Invalid JWT token");
                }
            }

            return message;
        } catch (JwtException e) {
            log.error("JWT validation failed: {}", e.getMessage());
            throw new BadCredentialsException("Invalid JWT token");
        } catch (AuthenticationServiceException e) {
            log.error("Room access validation failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during message processing: {}", e.getMessage());
            throw new MessagingException("Failed to process message", e);
        }
    }

    private void validateToken(StompHeaderAccessor accessor) {
        String bearerToken = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER);
        if (bearerToken == null || !bearerToken.startsWith(BEARER_PREFIX)) {
            throw new BadCredentialsException("Missing or invalid Authorization header");
        }

    }

    private void validateRoomAccess(String email, StompHeaderAccessor accessor) {
        String destination = accessor.getDestination();

        if (destination == null) {
            throw new MessagingException("Invalid destination");
        }

        String[] parts = destination.split(DESTINATION_DELIMITER);
        if (parts.length <= ROOM_ID_INDEX) {
            throw new MessagingException("Invalid destination format");
        }
        String roomId = parts[ROOM_ID_INDEX];
        boolean inRoom = chatRoomService.isUserInRoom(email, Long.parseLong(roomId));

        if(!inRoom) {
            throw new AccessDeniedException("채팅방 접근 권한이 없습니다.");
        }
    }
}