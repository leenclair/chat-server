package com.example.chatserver.interfaces.stomp;

import com.example.chatserver.application.message.MessageFacade;
import com.example.chatserver.interfaces.message.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageFacade messageFacade;

    @MessageMapping("/{roomId}")
//    @SendTo("/sub/{roomId}")
    public MessageDto.Main sendMessage(@DestinationVariable String roomId,
                            @Payload StompMessageDto requestMessage) {
        log.info("Received message for room {}: {}", roomId, requestMessage);

        MessageDto.Main response = messageFacade.saveMessage(requestMessage);
        // 메시지를 저장한 후, 해당 방의 구독자들에게 메시지를 전송
        messagingTemplate.convertAndSend("/sub/" + roomId, response);

        // 요청한 방의 메시지를 반환
        return response;
    }

}
