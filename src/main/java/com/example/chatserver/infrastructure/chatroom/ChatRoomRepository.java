package com.example.chatserver.infrastructure.chatroom;

import com.example.chatserver.domain.chatmessage.ChatMessage;
import com.example.chatserver.domain.chatroom.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    // 사용자가 참여 중인 채팅방 조회
    @Query("SELECT r FROM ChatRoom r " +
            "JOIN ChatMember m ON r.id = m.room.id " +
            "WHERE m.member.id = :userId AND m.leaveAt IS NULL")
    List<ChatRoom> findActiveRoomsByUserId(@Param("userId") Long userId);

    // 채팅방 이름으로 검색
    List<ChatRoom> findByNameContaining(String name);

    // 특정 타입의 채팅방 조회
    List<ChatRoom> findByType(ChatRoom.RoomType type);

    // 최근 메시지가 있는 채팅방 조회
    @Query("SELECT DISTINCT r FROM ChatRoom r " +
            "JOIN ChatMessage m ON r.id = m.room.id " +
            "WHERE m.createdAt > :since")
    List<ChatRoom> findRoomsWithRecentMessages(@Param("since") ZonedDateTime since);

    @Query("SELECT r FROM ChatRoom r " +
            "JOIN ChatMember m1 ON r.id = m1.room.id " +
            "JOIN ChatMember m2 ON r.id = m2.room.id " +
            "WHERE r.type = 'DIRECT' " +
            "AND m1.member.id = :memberId " +
            "AND m2.member.id = :opponentId " +
            "AND m1.leaveAt IS NULL " +
            "AND m2.leaveAt IS NULL")
    Optional<ChatRoom> findPrivateChatRoomByMembers(
            @Param("memberId") Long memberId,
            @Param("opponentId") Long opponentId);



}
