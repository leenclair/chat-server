package com.example.chatserver.domain.userroom;

import com.example.chatserver.domain.room.Room;

public interface UserRoomReader {
    Room findOneToOneRoom(Long user1Id, Long user2Id);
}
