package com.example.chatserver.application.message;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.message.Message;
import com.example.chatserver.domain.message.MessageService;
import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.profile.ProfileReader;
import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.room.RoomService;
import com.example.chatserver.domain.user.User;
import com.example.chatserver.domain.user.UserReader;
import com.example.chatserver.domain.user.UserService;
import com.example.chatserver.domain.userroom.UserRoom;
import com.example.chatserver.domain.userroom.UserRoomReader;
import com.example.chatserver.interfaces.message.MessageDto;
import com.example.chatserver.interfaces.stomp.StompMessageDto;
import com.example.chatserver.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageFacade {
    private final MessageService messageService;
    private final RoomService roomService;
    private final UserService userService;
    private final ProfileReader profileReader;
    private final UserRoomReader userRoomReader;
    private final UserReader userReader;

    @Transactional
    public MessageDto.Main saveMessage(StompMessageDto requestMessage) {
        // 현재 사용자의 이메일을 통해 User 객체를 가져온다.
        User sender = userReader.getUser(requestMessage.getSenderEmail());
        // 방이 존재하는지 확인한다.
        Room room = roomService.findByRoomId(requestMessage.getRoomId());
        UserRoom userRoom = userRoomReader.findByUserIdAndRoomId(sender.getId(), room.getId()).orElseThrow(
                () -> new EntityNotFoundException("UserRoom not found for user ID: "
                        + sender.getId() + " and room ID: " + room.getId())
        );

        Message initMessage = requestMessage.toEntity(room, sender);

        // 메시지를 저장한다.
        Message message = messageService.saveMessage(initMessage);

        // 메시지 저장 후, dto로 변환하여 반환한다.
        Profile profile = profileReader.getProfile(message.getSender().getId()).orElseThrow(
                () -> new EntityNotFoundException("Profile not found for user ID: " + message.getSender().getId())
        );

        int notReadCount = calculateNotReadCountForOneToOne(message, userRoom);

        return new MessageDto.Main(sender.getId(), message, profile, notReadCount);
    }

    public List<MessageDto.Main> getMessagesByRoomId(Long roomId) {
        // 현재 사용자가 속한 방의 메시지를 가져온다.
        Long currentUserId = getCurrentUserId();
        return messageService.getMessagesByRoomId(currentUserId, roomId);
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((CustomUserDetails) authentication.getPrincipal()).getUser().getId();
    }

    private int calculateNotReadCountForOneToOne(Message message, UserRoom userRoom) {
        // 내가 보낸 메시지는 읽지 않은 것으로 카운트하지 않음
        if (message.getSender().getId().equals(userRoom.getUser().getId())) {
            return 0;
        }

        // 메시지가 마지막으로 읽은 메시지보다 나중에 온 것인지 확인
        if (userRoom.getLastReadMessageId() == null ||
                message.getId() > userRoom.getLastReadMessageId()) {
            return 1;
        }

        return 0;
    }

}
