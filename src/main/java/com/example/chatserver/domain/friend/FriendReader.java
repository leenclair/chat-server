package com.example.chatserver.domain.friend;

import java.util.List;

public interface FriendReader {
    List<Friend> findAllByReceiverId(Long userId);
}
