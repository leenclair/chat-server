package com.example.chatserver.domain.chatmessage;

import com.example.chatserver.domain.chatmember.ChatMember;
import com.example.chatserver.domain.chatroom.ChatRoom;

import java.util.List;

public interface ChatMessageReader {
    ChatRoom findRoomById(Long roomId);
    List<ChatMember> findActiveMembersByRoomId(Long roomId);
    boolean isUserInRoom(String userEmail, Long roomId);
    List<ChatMessage> getMessagesByRoomId(Long roomId);
}
