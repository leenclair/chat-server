package com.example.chatserver.domain.chatroom;

public interface ChatRoomService {
    ChatRoomInfo.PrivateChatRoomInfo checkPrivateChatRoom(Long otherMemberId);
}
