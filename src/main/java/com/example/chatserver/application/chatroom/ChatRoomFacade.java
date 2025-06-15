package com.example.chatserver.application.chatroom;

import com.example.chatserver.domain.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomFacade {
    private final ChatRoomService chatRoomService;

}
