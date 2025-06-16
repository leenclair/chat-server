package com.example.chatserver.domain.chatroom;

public interface ChatRoomStore {
    ChatRoom createPrivateChatRoom(Long member1Id, Long member2Id);
    
}
