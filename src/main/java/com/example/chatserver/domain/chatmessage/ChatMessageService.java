package com.example.chatserver.domain.chatmessage;

import com.example.chatserver.domain.chatroom.ChatRoomInfo;

import java.util.List;

public interface ChatMessageService {
    ChatMessage processMessage(Long roomId, ChatMessageCommand.RegisterMessage command);
    boolean isUserInRoom(String userEmail, Long roomId);
    List<ChatRoomInfo.MessageResponse> findMessagesByRoomId(Long roomId);
}
