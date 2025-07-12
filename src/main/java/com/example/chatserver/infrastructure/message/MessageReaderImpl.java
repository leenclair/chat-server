package com.example.chatserver.infrastructure.message;

import com.example.chatserver.domain.message.Message;
import com.example.chatserver.domain.message.MessageReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageReaderImpl implements MessageReader {
    private final MessageRepository messageRepository;


    @Override
    public List<Message> getMessagesByRoomId(Long roomId) {
        return messageRepository.findByRoomIdOrderByCreatedAtAsc(roomId);
    }
}
