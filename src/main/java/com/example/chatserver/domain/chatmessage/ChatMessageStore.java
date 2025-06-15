package com.example.chatserver.domain.chatmessage;

public interface ChatMessageStore {
    ChatMessage saveMessage(ChatMessageCommand.RegisterMessage command);
}
