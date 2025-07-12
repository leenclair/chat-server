package com.example.chatserver.infrastructure.userroom;

import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.userroom.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRoomRepository extends JpaRepository<UserRoom,Long> {
    @Query("SELECT r FROM Room r " +
            "WHERE r.type = 'ONE_TO_ONE' " +
            "AND r.id IN (" +
            "   SELECT ur1.room.id FROM UserRoom ur1 WHERE ur1.user.id = :user1Id" +
            ") " +
            "AND r.id IN (" +
            "   SELECT ur2.room.id FROM UserRoom ur2 WHERE ur2.user.id = :user2Id" +
            ") " +
            "AND r.status = 'ACTIVE'")
    Room findOneToOneRoomByUsers(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);
    @Query("SELECT ur FROM UserRoom ur " +
            "JOIN FETCH ur.user " +
            "WHERE ur.user.id = :userId AND ur.room.id = :roomId")
    Optional<UserRoom> findByUserIdAndRoomId(Long userId, Long roomId);
}
