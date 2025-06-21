package com.example.chatserver.interfaces.chatroom;

import com.example.chatserver.application.chatmessage.ChatMessageFacade;
import com.example.chatserver.application.chatroom.ChatRoomFacade;
import com.example.chatserver.domain.chatroom.ChatRoomInfo;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat-rooms")
public class ChatRoomApiController {
    private final ChatRoomFacade chatRoomFacade;
    private final ChatMessageFacade chatMessageFacade;

    @PostMapping("/private/check")
    public ResponseEntity<?> getDirectChatRooms(@RequestParam(value = "memberId", required = false) Long opponentMemberId) {
        log.info("Fetching direct chat rooms, requested opponent token: {}", opponentMemberId);
        var info = chatRoomFacade.checkPrivateChatRoom(opponentMemberId);
        var response = new ChatRoomDto.ChatRoomResponse(info);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getChatRoomMessages(@PathVariable Long roomId) {
        log.info("Fetching messages for chat room with ID: {}", roomId);
        List<ChatRoomInfo.MessageResponse> info = chatMessageFacade.getChatRoomMessages(roomId);
        Member currentMember = SecurityUtil.getCurrentMember();
        var response = info.stream()
                .map(messageInfo -> new ChatRoomDto.retrieveMessagesResponse(messageInfo, currentMember.getId()))
                .toList();
        log.info("Retrieved {} messages for chat room ID: {}", response.size(), roomId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
