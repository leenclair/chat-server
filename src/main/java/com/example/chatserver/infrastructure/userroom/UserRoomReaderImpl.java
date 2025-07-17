package com.example.chatserver.infrastructure.userroom;

import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.userroom.UserRoom;
import com.example.chatserver.domain.userroom.UserRoomReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRoomReaderImpl implements UserRoomReader {

    private final UserRoomRepository userRoomRepository;

    @Override
    public Room findOneToOneRoom(Long user1Id, Long user2Id) {
        return userRoomRepository.findOneToOneRoomByUsers(user1Id,user2Id);
    }

    @Override
    public Optional<UserRoom> findByUserIdAndRoomId(Long userId, Long roomId) {
        return userRoomRepository.findByUserIdAndRoomId(userId, roomId);
    }

}
