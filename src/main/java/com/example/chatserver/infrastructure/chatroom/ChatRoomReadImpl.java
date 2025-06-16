package com.example.chatserver.infrastructure.chatroom;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.chatroom.ChatRoom;
import com.example.chatserver.domain.chatroom.ChatRoomReader;
import com.example.chatserver.infrastructure.chatmember.ChatMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChatRoomReadImpl implements ChatRoomReader {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMemberRepository chatMemberRepository;

    @Override
    public ChatRoom findByRoomId(Long roomId) {
        return chatRoomRepository.findById(roomId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<ChatRoom> findPrivateChatRoom(Long memberId, Long opponentId) {
        return chatRoomRepository.findPrivateChatRoomByMembers(memberId, opponentId);
    }

    @Override
    public boolean isMemberInRoom(Long memberId, Long roomId) {
        return chatMemberRepository.existsByUserIdAndRoomIdAndLeaveAtIsNull(memberId, roomId);
    }

}
