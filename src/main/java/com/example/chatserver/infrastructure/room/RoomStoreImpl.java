package com.example.chatserver.infrastructure.room;

import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.room.RoomStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoomStoreImpl implements RoomStore {
    private final RoomRepository roomRepository;

    @Override
    public Room store(Room room) {
        return roomRepository.save(room);
    }
}
