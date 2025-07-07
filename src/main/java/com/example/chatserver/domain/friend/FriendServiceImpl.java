package com.example.chatserver.domain.friend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final FriendReader friendReader;
    private final FriendStore friendStore;

    @Override
    public List<Friend> findAllByReceiverId(Long receiverId) {
        return friendReader.findAllByReceiverId(receiverId);
    }

    @Override
    public Friend save(Friend friend) {
        return friendStore.store(friend);
    }
}
