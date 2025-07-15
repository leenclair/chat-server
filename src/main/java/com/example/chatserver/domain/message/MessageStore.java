package com.example.chatserver.domain.message;

public interface MessageStore {
    Message store(Message message);
}
