package com.example.chatserver.infrastructure.chatroom;

import com.example.chatserver.domain.chatroom.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    // 사용자가 참여 중인 채팅방 조회
    @Query("SELECT r FROM ChatRoom r " +
            "JOIN ChatMember m ON r.id = m.room.id " +
            "WHERE m.userId = :userId AND m.leaveAt IS NULL")
    List<ChatRoom> findActiveRoomsByUserId(@Param("userId") Long userId);

    // 채팅방 이름으로 검색
    List<ChatRoom> findByNameContaining(String name);

    // 특정 타입의 채팅방 조회
    List<ChatRoom> findByType(ChatRoom.RoomType type);

    // 최근 메시지가 있는 채팅방 조회
    @Query("SELECT DISTINCT r FROM ChatRoom r " +
            "JOIN ChatMessage m ON r.id = m.roomId " +
            "WHERE m.createdAt > :since")
    List<ChatRoom> findRoomsWithRecentMessages(@Param("since") ZonedDateTime since);
}
