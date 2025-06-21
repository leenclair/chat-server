package com.example.chatserver.domain.chatroom;

public interface ChatRoomService {
    ChatRoomInfo.PrivateChatRoomInfo checkPrivateChatRoom(Long otherMemberId);
    // 사용자가 해당 room에 있는지 확인하는 메소드 추가
    boolean isUserInRoom(String email, Long roomId);

}
