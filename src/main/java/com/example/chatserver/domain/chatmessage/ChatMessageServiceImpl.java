package com.example.chatserver.domain.chatmessage;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.chatmember.ChatMember;
import com.example.chatserver.domain.chatroom.ChatRoom;
import com.example.chatserver.domain.chatroom.ChatRoomInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageReader chatMessageReader;
    private final ChatMessageStore chatMessageStore;

    @Transactional
    public ChatMessage processMessage(Long roomId, ChatMessageCommand.RegisterMessage command) {
        // 1. 방 존재 여부 확인
        ChatRoom room = chatMessageReader.findRoomById(roomId);
        if(room==null){
            throw new EntityNotFoundException("roomId:" + roomId + "에 해당하는 방이 존재하지 않습니다.");
        }

        // 2. 사용자가 방에 있는지 확인
        if (!chatMessageReader.isUserInRoom(command.getSenderId(), roomId)) {
            throw new EntityNotFoundException("roomId:" + roomId + "에 해당하는 사용자:" + command.getSenderId()  + " 가 방에 없습니다.");
        }

        // 3. 메시지 저장
        ChatMessage message = chatMessageStore.saveMessage(command);

        // 4. 활성 멤버 조회
        List<ChatMember> activeMembers = chatMessageReader.findActiveMembersByRoomId(roomId);

        // Todo 알림 처리

        return message;
    }

    @Override
    public boolean isUserInRoom(Long userId, Long roomId) {
        // 1. 방 존재 여부 확인
        ChatRoom room = chatMessageReader.findRoomById(roomId);
        if(room==null){
            log.info("roomId:{} 에 해당하는 방이 존재하지 않습니다.", roomId);
            return false;
        }

        // 2. 사용자가 방에 있는지 확인
        if (!chatMessageReader.isUserInRoom(userId, roomId)) {
            log.info("roomId:{} 에 해당하는 사용자: {}가 방에 없습니다.", roomId, userId);
            return false;
        }else return true;
    }

    @Override
    public List<ChatRoomInfo.MessageResponse> findMessagesByRoomId(Long roomId) {
        List<ChatMessage> messages = chatMessageReader.getMessagesByRoomId(roomId);
        return messages.stream()
                .map(ChatRoomInfo.MessageResponse::new)
                .toList();

    }

}
