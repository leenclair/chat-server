package com.example.chatserver.interfaces.stomp;

import com.example.chatserver.application.message.MessageFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageFacade messageFacade;

    @MessageMapping("/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, StompMessageDto requestMessage) {
        log.info("Received message for room {}: {}", roomId, requestMessage);

        messageFacade.saveMessage(requestMessage);

    }

}
