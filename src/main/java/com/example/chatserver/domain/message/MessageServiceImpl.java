package com.example.chatserver.domain.message;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.profile.ProfileReader;
import com.example.chatserver.domain.userroom.UserRoom;
import com.example.chatserver.domain.userroom.UserRoomReader;
import com.example.chatserver.interfaces.message.MessageDto;
import com.example.chatserver.interfaces.stomp.StompMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageReader messageReader;
    private final MessageStore messageStore;
    private final ProfileReader profileReader;
    private final UserRoomReader userRoomReader;

    @Override
    public List<MessageDto.Main> getMessagesByRoomId(Long currentUserId, Long roomId) {
        //현재 사용자가 속한 방의 메시지를 가져온다. 채팅방 있는지 확인 필요
        UserRoom userRoom = userRoomReader.findByUserIdAndRoomId(currentUserId, roomId)
                .orElseThrow(() -> new EntityNotFoundException("UserRoom not found for user ID: " + currentUserId + " and room ID: " + roomId));

        List<Message> messages = messageReader.getMessagesByRoomId(roomId);
        return messages.stream()
                .map(message -> {
                    Profile profile = profileReader.getProfile(message.getSender().getId());
                    if(profile ==null){ throw new EntityNotFoundException("Profile not found for user ID: " + message.getSender().getId()); }
                    int notReadCount = calculateNotReadCountForOneToOne(message, userRoom);

                    return new MessageDto.Main(currentUserId, message, profile, notReadCount);
                }).toList();
    }

    @Override
    public void saveMessage(Message message) {
        Message saveMessage = messageStore.store(message);
        log.info("save message, result id:{}", saveMessage.getId());
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
