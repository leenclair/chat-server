package com.example.chatserver.infrastructure.chatmember;

import com.example.chatserver.domain.chatmember.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    // 활성 멤버 조회
    List<ChatMember> findByRoomIdAndLeaveAtIsNull(Long roomId);

    // 특정 사용자의 채팅방 참여 여부 확인
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END " +
    "FROM ChatMember m " +
    "WHERE m.member.email = :userEmail " +
    "AND m.room.id = :roomId " +
    "AND m.leaveAt IS NULL"
    )
    boolean existsByEmailAndRoomIdAndLeaveAtIsNull(String userEmail, Long roomId);

    // 채팅방의 멤버 수 조회
    long countByRoomIdAndLeaveAtIsNull(Long roomId);

    // 특정 시간 이후 참여한 멤버 조회
    @Query("SELECT m FROM ChatMember m WHERE m.room.id = :roomId " +
            "AND m.joinAt > :since")
    List<ChatMember> findNewMembers(@Param("roomId") Long roomId,
                                    @Param("since") LocalDateTime since);

    // 해당 채팅방에 아직 남아있고, 강퇴당하지 않은 유저들을 입장 시간 순으로 리스트로 가져온다.
    @Query("SELECT m FROM ChatMember m " +
            "WHERE m.room.id = :roomId " +
            "AND m.leaveAt IS NULL " +
            "AND m.kicked = false " +
            "ORDER BY m.joinAt ASC")
    List<ChatMember> findActiveMembersByRoomId(@Param("roomId") Long roomId);
}
