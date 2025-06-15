package com.example.chatserver.interfaces.stomp;

import com.example.chatserver.application.chatmessage.ChatMessageFacade;
import com.example.chatserver.domain.chatmessage.ChatMessageCommand;
import com.example.chatserver.domain.chatmessage.ChatMessageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatMessageFacade chatMessageFacade;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{roomId}/message")
    public void sendMessage(@DestinationVariable String roomId, ChatDto.ChatMessageRequest request) {
        // 여기에 메시지를 처리하는 로직을 추가합니다.
        // 예: 메시지를 데이터베이스에 저장하고, 해당 채팅방에 있는 모든 사용자에게 메시지를 전송합니다.
        log.info("Received message for room: {}, Payload: {}", roomId, request);
        var command = request.toCommand();
        var chatMessageInfo = chatMessageFacade.handleMessage(roomId, command);
        var response = new ChatDto.ChatMessageResponse(chatMessageInfo);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonResponse = objectMapper.writeValueAsString(response);
            log.info("Sending response: {}", jsonResponse);
            messagingTemplate.convertAndSend(
                    "/topic/chat/" + roomId,
                    response
            );
            log.debug("Message processed and sent successfully for room: {}", roomId);
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage());
        }
    }

}
