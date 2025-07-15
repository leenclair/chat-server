package com.example.chatserver.interfaces.message;

import com.example.chatserver.application.message.MessageFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageApiController {

    private final MessageFacade messageFacade;

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<?> getRoomMessages(@PathVariable("roomId") Long roomId) {
        var response = messageFacade.getMessagesByRoomId(roomId);
        return ResponseEntity.ok(response);
    }

}
