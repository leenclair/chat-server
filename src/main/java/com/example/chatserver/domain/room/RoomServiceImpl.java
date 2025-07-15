package com.example.chatserver.domain.room;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.profile.ProfileReader;
import com.example.chatserver.domain.user.User;
import com.example.chatserver.domain.user.UserReader;
import com.example.chatserver.domain.userroom.UserRoom;
import com.example.chatserver.domain.userroom.UserRoomReader;
import com.example.chatserver.domain.userroom.UserRoomStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final UserRoomReader userRoomReader;
    private final RoomStore roomStore;
    private final ProfileReader profileReader;
    private final UserRoomStore userRoomStore;
    private final UserReader userReader;
    private final RoomReader roomReader;

    @Override
    @Transactional
    public Room findOrCreateOneToOneRoom(Long user1Id, Long user2Id) {
        Room existingRoom = userRoomReader.findOneToOneRoom(user1Id, user2Id);

        if (existingRoom != null) {
            return existingRoom;
        }

        String nickname1 = profileReader.getNickname(user1Id);
        String nickname2 = profileReader.getNickname(user2Id);
        if(StringUtils.isBlank(nickname1) || StringUtils.isBlank(nickname2)){
            throw new EntityNotFoundException("User nickname not found");
        }

        Room room = Room.builder()
                .name(nickname1 + "," + nickname2)
                .type("ONE_TO_ONE")
                .status(Room.Status.ACTIVE)
                .build();

        Room savedRoom = roomStore.store(room);
        createUserRoom(user1Id, savedRoom);
        createUserRoom(user2Id, savedRoom);

        return savedRoom;
    }

    @Override
    public Room findByRoomId(Long roomId) {
        return roomReader.readRoom(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with ID: " + roomId));
    }

    private void createUserRoom(Long userId, Room room) {
        User user = userReader.getUserById(userId);

        UserRoom userRoom = UserRoom.builder()
                .user(user)
                .room(room)
                .notReadChat(0L)
                .joinedAt(LocalDateTime.now())
                .isLeft(false)
                .build();

        userRoomStore.store(userRoom);
    }
}
