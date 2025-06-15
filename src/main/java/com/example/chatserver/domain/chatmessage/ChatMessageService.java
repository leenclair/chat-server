package com.example.chatserver.domain.chatmessage;

public interface ChatMessageService {
    ChatMessage processMessage(Long roomId, ChatMessageCommand.RegisterMessage command);
}
