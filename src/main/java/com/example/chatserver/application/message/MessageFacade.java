package com.example.chatserver.application.message;

import com.example.chatserver.domain.message.MessageService;
import com.example.chatserver.interfaces.message.MessageDto;
import com.example.chatserver.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageFacade {
    private final MessageService messageService;

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
