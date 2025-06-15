package com.example.chatserver.infrastructure.chatroom;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.chatroom.ChatRoom;
import com.example.chatserver.domain.chatroom.ChatRoomReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomReadImpl implements ChatRoomReader {
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom findByRoomId(Long roomId) {
        return chatRoomRepository.findById(roomId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
