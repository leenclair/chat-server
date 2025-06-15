package com.example.chatserver.domain.chatroom;

public interface ChatRoomReader {
    ChatRoom findByRoomId(Long roomId);
}
