package com.example.chatserver.domain.chatmessage;

import com.example.chatserver.domain.chatroom.ChatRoom;
import com.example.chatserver.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ChatMessageCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterMessage {
        private Long roomId;
        private String senderEmail;
        private String content;

        public ChatMessage toEntity(Member sender, ChatRoom chatRoom) {
            return ChatMessage.builder()
                    .room(chatRoom)
                    .sender(sender)
                    .content(content)
                    .build();
        }
    }

}
