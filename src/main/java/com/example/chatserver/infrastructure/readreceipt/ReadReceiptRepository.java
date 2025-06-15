package com.example.chatserver.infrastructure.readreceipt;

import com.example.chatserver.domain.readreceipt.ReadReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface ReadReceiptRepository extends JpaRepository<ReadReceipt, Long> {
    // 메시지의 읽음 상태 조회
    List<ReadReceipt> findByMessageId(Long messageId);

    // 사용자의 읽지 않은 메시지 수 조회
    @Query("SELECT COUNT(r) FROM ReadReceipt r " +
            "WHERE r.userId = :userId AND r.readAt IS NULL")
    long countUnreadMessages(@Param("userId") Long userId);

    // 특정 시간 이후의 읽음 상태 조회
    List<ReadReceipt> findByUserIdAndReadAtAfter(Long userId, ZonedDateTime after);

    // 메시지의 마지막 읽음 시간 조회
    @Query("SELECT MAX(r.readAt) FROM ReadReceipt r " +
            "WHERE r.message.id = :messageId")
    ZonedDateTime findLastReadTime(@Param("messageId") Long messageId);

}
