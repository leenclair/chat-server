package com.example.chatserver.infrastructure.friend;

import com.example.chatserver.domain.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Long> {
    List<Friend> findAllByReceiverId(Long receiverId);
}
