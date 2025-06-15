package com.example.chatserver.domain.chatmessage;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ChatMessageCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterMessage {
        private Long roomId;
        private Long senderId;
        private String content;

        public ChatMessage toEntity() {
            return ChatMessage.builder()
                    .senderId(senderId)
                    .content(content)
                    .build();
        }
    }

}
