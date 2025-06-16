package com.example.chatserver.application.chatroom;

import com.example.chatserver.domain.chatroom.ChatRoomInfo;
import com.example.chatserver.domain.chatroom.ChatRoomService;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomFacade {
    private final ChatRoomService chatRoomService;

    public ChatRoomInfo.PrivateChatRoomInfo checkPrivateChatRoom(Long otherMemberId) {
        log.info("checkPrivateChatRoom, requested other member ID: {}", otherMemberId);
        return chatRoomService.checkPrivateChatRoom(otherMemberId);
    }

    public ChatRoomInfo.MessageResponse getMessagesInRoom(Long roomId) {
        log.info("getMessagesInRoom, requested room ID: {}", roomId);
        return null;
    }

}
