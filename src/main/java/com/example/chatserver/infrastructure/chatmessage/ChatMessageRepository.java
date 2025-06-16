package com.example.chatserver.infrastructure.chatmessage;

import com.example.chatserver.domain.chatmessage.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    // 기본적인 메시지 조회
    List<ChatMessage> findByRoomIdOrderByCreatedAtDesc(Long roomId);

    // 특정 시간 이후의 메시지 조회
    List<ChatMessage> findByRoomIdAndCreatedAtAfterOrderByCreatedAtAsc(
            Long roomId, ZonedDateTime after);

    // 특정 사용자의 메시지 조회
    List<ChatMessage> findBySenderIdAndRoomIdOrderByCreatedAtDesc(
            Long senderId, Long roomId);

    // 메시지 존재 여부 확인
    boolean existsByRoomIdAndSenderId(Long roomId, Long senderId);

    // 특정 시간 범위의 메시지 조회
    @Query("SELECT m FROM ChatMessage m WHERE m.room.id = :roomId " +
            "AND m.createdAt BETWEEN :startTime AND :endTime " +
            "ORDER BY m.createdAt DESC")
    List<ChatMessage> findMessagesInTimeRange(
            @Param("roomId") Long roomId,
            @Param("startTime") ZonedDateTime startTime,
            @Param("endTime") ZonedDateTime endTime);

    // 특정 방의 메시지들 시간순으로 가져오기
    List<ChatMessage> findByIdOrderByCreatedAtAsc(Long roomId);

}
