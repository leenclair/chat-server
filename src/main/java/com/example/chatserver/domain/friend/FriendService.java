package com.example.chatserver.domain.friend;

import java.util.List;

public interface FriendService {
    List<Friend> findAllByReceiverId(Long receiverId);
    Friend save(Friend friend);
}
