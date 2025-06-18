package com.example.chatserver.domain.chatmessage;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ChatMessageInfo {

    private final Long messageId;
    private final Long roomId;
    private final Long senderId;
    private final String content;
    private final ZonedDateTime createdAt;
    private final ChatMessage.MessageStatus status;

    public ChatMessageInfo(ChatMessage message) {
        this.messageId = message.getId();
        this.roomId = message.getRoom().getId();
        this.senderId = message.getSender().getId();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.status = message.getStatus();
    }

}
