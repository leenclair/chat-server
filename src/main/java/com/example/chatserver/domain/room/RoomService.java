package com.example.chatserver.domain.room;

public interface RoomService {
    Room findOrCreateOneToOneRoom(Long user1Id, Long user2Id);
    

}
