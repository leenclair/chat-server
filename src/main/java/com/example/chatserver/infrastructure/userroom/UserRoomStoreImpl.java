package com.example.chatserver.infrastructure.userroom;

import com.example.chatserver.domain.userroom.UserRoom;
import com.example.chatserver.domain.userroom.UserRoomStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRoomStoreImpl implements UserRoomStore {
    private final UserRoomRepository userRoomRepository;

    @Override
    public UserRoom store(UserRoom userRoom) {
        return userRoomRepository.save(userRoom);
    }
}
