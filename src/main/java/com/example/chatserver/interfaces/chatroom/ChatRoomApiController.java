package com.example.chatserver.interfaces.chatroom;

import com.example.chatserver.application.chatroom.ChatRoomFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat-rooms")
public class ChatRoomApiController {
    private final ChatRoomFacade chatRoomFacade;

    @PostMapping("/private/check")
    public String getDirectChatRooms(@RequestParam(value = "memberId", required = false) Long opponentMemberId) {
        log.info("Fetching direct chat rooms, requested opponent token: {}", opponentMemberId);
        chatRoomFacade.checkPrivateChatRoom(opponentMemberId);

        return "List of direct chat rooms";
    }

    @GetMapping("/{roomId}/messages")
    public String getChatRoomMessages(@PathVariable Long roomId) {
        log.info("Fetching messages for chat room with ID: {}", roomId);
        chatRoomFacade.getMessagesInRoom(roomId);

        return "List of messages in chat room";
    }
}
