package com.example.chatserver.infrastructure.room;

import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.room.RoomReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoomReaderImpl implements RoomReader {
    private final RoomRepository roomRepository;

    @Override
    public Optional<Room> readRoom(Long roomId) {
        return roomRepository.findById(roomId);
    }
}
