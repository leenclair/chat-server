package com.example.chatserver.domain.chatroom;

import com.example.chatserver.domain.member.Member;

public interface ChatRoomStore {
    ChatRoom createPrivateChatRoom(Member member1, Member member2);
    
}
