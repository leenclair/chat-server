package com.example.chatserver.infrastructure.userroom;

import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.userroom.UserRoomReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRoomReaderImpl implements UserRoomReader {

    private final UserRoomRepository userRoomRepository;

    @Override
    public Room findOneToOneRoom(Long user1Id, Long user2Id) {
        return userRoomRepository.findOneToOneRoomByUsers(user1Id,user2Id);
    }
}
