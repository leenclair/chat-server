package com.example.chatserver.infrastructure.chatmessage;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.chatmember.ChatMember;
import com.example.chatserver.domain.chatmessage.ChatMessage;
import com.example.chatserver.domain.chatmessage.ChatMessageReader;
import com.example.chatserver.domain.chatroom.ChatRoom;
import com.example.chatserver.infrastructure.chatmember.ChatMemberRepository;
import com.example.chatserver.infrastructure.chatroom.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatMessageReadImpl implements ChatMessageReader {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatRoom findRoomById(Long roomId) {
        return chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("roomId " + roomId + "에 해당하는 채팅방이 없습니다."));
    }

    @Override
    public List<ChatMember> findActiveMembersByRoomId(Long roomId) {
        return chatMemberRepository.findByRoomIdAndLeaveAtIsNull(roomId);
    }

    @Override
    public boolean isUserInRoom(Long userId, Long roomId) {
        return chatMemberRepository.existsByUserIdAndRoomIdAndLeaveAtIsNull(userId, roomId);
    }

    @Override
    public List<ChatMessage> getMessagesByRoomId(Long roomId) {
        return chatMessageRepository.findByIdOrderByCreatedAtAsc(roomId);
    }

}
