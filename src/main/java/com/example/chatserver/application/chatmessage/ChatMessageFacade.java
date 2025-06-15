package com.example.chatserver.application.chatmessage;

import com.example.chatserver.domain.chatmessage.ChatMessage;
import com.example.chatserver.domain.chatmessage.ChatMessageCommand;
import com.example.chatserver.domain.chatmessage.ChatMessageInfo;
import com.example.chatserver.domain.chatmessage.ChatMessageService;
import com.example.chatserver.interfaces.stomp.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageFacade {
    private final ChatMessageService chatMessageService;

    public ChatMessageInfo handleMessage(String roomId, ChatMessageCommand.RegisterMessage command) {
        ChatMessage message = chatMessageService.processMessage(Long.parseLong(roomId), command);

        // 3. 결과를 Response DTO로 변환
        return new ChatMessageInfo(message);
    }
}
