package com.example.chatserver.domain.chatroom;

import java.util.Optional;

public interface ChatRoomReader {
    ChatRoom findByRoomId(Long roomId);
    Optional<ChatRoom> findPrivateChatRoom(Long memberId, Long opponentId);
    boolean isMemberInRoom(Long memberId, Long roomId);
}
