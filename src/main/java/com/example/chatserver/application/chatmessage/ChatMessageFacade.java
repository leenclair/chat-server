package com.example.chatserver.application.chatmessage;

import com.example.chatserver.domain.chatmessage.ChatMessage;
import com.example.chatserver.domain.chatmessage.ChatMessageCommand;
import com.example.chatserver.domain.chatmessage.ChatMessageInfo;
import com.example.chatserver.domain.chatmessage.ChatMessageService;
import com.example.chatserver.domain.chatroom.ChatRoomInfo;
import com.example.chatserver.domain.chatroom.ChatRoomReader;
import com.example.chatserver.domain.chatroom.ChatRoomService;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageFacade {
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final ChatRoomReader chatRoomReader;

    public ChatMessageInfo handleMessage(String roomId, ChatMessageCommand.RegisterMessage command) {
        ChatMessage message = chatMessageService.processMessage(Long.parseLong(roomId), command);

        // 3. 결과를 Response DTO로 변환
        return new ChatMessageInfo(message);
    }

    public List<ChatRoomInfo.MessageResponse> getChatRoomMessages(Long roomId) {
        // 1. 현재 사용자가 해당 채팅방의 멤버인지 확인
        Member currentMember = SecurityUtil.getCurrentMember();
        if (currentMember == null) {
            throw new IllegalArgumentException("현재 사용자가 인증되지 않았습니다.");
        }
        log.info("현재 사용자: {}, 채팅방 ID: {}", currentMember.getId(), roomId);

        // 2. 채팅방의 메시지 목록 조회
        return chatMessageService.findMessagesByRoomId(roomId);
    }

}
