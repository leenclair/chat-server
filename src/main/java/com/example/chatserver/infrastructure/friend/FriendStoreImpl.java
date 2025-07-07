package com.example.chatserver.infrastructure.friend;

import com.example.chatserver.domain.friend.Friend;
import com.example.chatserver.domain.friend.FriendStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FriendStoreImpl implements FriendStore {
    private final FriendRepository friendRepository;

    @Override
    public Friend store(Friend friend) {
        return friendRepository.save(friend);
    }
}
