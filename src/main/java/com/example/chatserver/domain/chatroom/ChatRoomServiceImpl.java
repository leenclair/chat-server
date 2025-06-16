package com.example.chatserver.domain.chatroom;

import com.example.chatserver.domain.member.Member;
import com.example.chatserver.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomReader chatRoomReader;
    private final ChatRoomStore chatRoomStore;

    @Override
    public ChatRoomInfo.PrivateChatRoomInfo checkPrivateChatRoom(Long otherMemberId) {
        // 1. 현재 로그인한 사용자 정보 가져오기
        Member currentMember = SecurityUtil.getCurrentMember();
        log.info("checkPrivateChatRoom, currentMember: {}", currentMember);

        // 2. 기존 1:1 채팅방 조회
        Optional<ChatRoom> existingRoom = chatRoomReader.findPrivateChatRoom(currentMember.getId(), otherMemberId);

        if (existingRoom.isPresent()) {
            // 3. 기존 채팅방이 있는 경우
            return ChatRoomInfo.PrivateChatRoomInfo.existing(existingRoom.get().getId());
        } else {
            // 4. 새로운 채팅방 생성
            ChatRoom newRoom = chatRoomStore.createPrivateChatRoom(currentMember.getId(), otherMemberId);
            return ChatRoomInfo.PrivateChatRoomInfo.newRoom(newRoom.getId());
        }
    }

}
