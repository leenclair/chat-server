package com.example.chatserver.domain.room;

import java.util.Optional;

public interface RoomReader {
    Optional<Room> readRoom(Long roomId);
}
