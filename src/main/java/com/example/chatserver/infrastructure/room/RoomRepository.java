package com.example.chatserver.infrastructure.room;

import com.example.chatserver.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {

}
