package com.example.chatserver.domain.userroom;

import com.example.chatserver.domain.room.Room;

import java.util.Optional;

public interface UserRoomReader {
    Room findOneToOneRoom(Long user1Id, Long user2Id);
    Optional<UserRoom> findByUserIdAndRoomId(Long userId, Long roomId);
}
