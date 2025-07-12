package com.example.chatserver.infrastructure.message;

import com.example.chatserver.domain.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query("SELECT m FROM Message m " +
            "JOIN FETCH m.sender " +
            "WHERE m.room.id = :roomId " +
            "ORDER BY m.createdAt ASC")
    List<Message> findByRoomIdOrderByCreatedAtAsc(Long roomId);
}
