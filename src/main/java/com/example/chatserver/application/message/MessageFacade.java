package com.example.chatserver.application.message;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.message.Message;
import com.example.chatserver.domain.message.MessageService;
import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.room.RoomService;
import com.example.chatserver.domain.user.User;
import com.example.chatserver.domain.user.UserService;
import com.example.chatserver.interfaces.message.MessageDto;
import com.example.chatserver.interfaces.stomp.StompMessageDto;
import com.example.chatserver.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageFacade {
    private final MessageService messageService;
    private final RoomService roomService;
    private final UserService userService;

    public void saveMessage(StompMessageDto requestMessage) {
        // 현재 사용자의 정보를 가져온다.
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User sender = userDetails.getUser();
        // 방이 존재하는지 확인한다.
        Room room = roomService.findByRoomId(requestMessage.getRoomId());

        Message initMessage = requestMessage.toEntity(room, sender);

        // 메시지를 저장한다.
        messageService.saveMessage(initMessage);
    }

    public List<MessageDto.Main> getMessagesByRoomId(Long roomId) {
        // 현재 사용자가 속한 방의 메시지를 가져온다.
        Long currentUserId = getCurrentUserId();
        return messageService.getMessagesByRoomId(currentUserId, roomId);
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((CustomUserDetails) authentication.getPrincipal()).getUser().getId();
    }

}
