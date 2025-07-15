package com.example.chatserver.infrastructure.message;

import com.example.chatserver.domain.message.Message;
import com.example.chatserver.domain.message.MessageStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageStoreImpl implements MessageStore {
    private final MessageRepository messageRepository;

    @Override
    public Message store(Message message) {
        return messageRepository.save(message);
    }
}
