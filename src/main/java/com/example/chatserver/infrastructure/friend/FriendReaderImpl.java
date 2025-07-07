package com.example.chatserver.infrastructure.friend;

import com.example.chatserver.domain.friend.Friend;
import com.example.chatserver.domain.friend.FriendReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FriendReaderImpl implements FriendReader {
    private final FriendRepository friendRepository;


    @Override
    public List<Friend> findAllByReceiverId(Long userId) {
        return friendRepository.findAllByReceiverId(userId);
    }
}
