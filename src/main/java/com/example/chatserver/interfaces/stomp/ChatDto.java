package com.example.chatserver.interfaces.stomp;

import com.example.chatserver.domain.chatmessage.ChatMessageCommand;
import com.example.chatserver.domain.chatmessage.ChatMessageInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.format.DateTimeFormatter;

public class ChatDto {

    @Getter
    @Setter
    @ToString
    public static class ChatMessageRequest {
        @NotNull(message = "roomId 는 필수값입니다")
        private Long roomId;
        @NotBlank(message = "sender 는 필수값입니다")
        private String senderEmail;
        @NotBlank(message = "content 는 필수값입니다")
        private String content;

        public ChatMessageCommand.RegisterMessage toCommand() {
            return ChatMessageCommand.RegisterMessage.builder()
                    .roomId(roomId)
                    .senderEmail(senderEmail)
                    .content(content)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class ChatMessageResponse {
        private final Long messageId;
        private final Long roomId;
        private final String senderEmail;
        private final String senderNickname;
        private final String content;
        private final String createdAt;
        private final String status;

        public ChatMessageResponse(ChatMessageInfo chatMessageInfo) {
            this.messageId = chatMessageInfo.getMessageId();
            this.roomId = chatMessageInfo.getRoomId();
            this.senderEmail = chatMessageInfo.getSenderEmail();
            this.senderNickname = chatMessageInfo.getSenderNickname();
            this.content = chatMessageInfo.getContent();
            this.createdAt = chatMessageInfo.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.status = chatMessageInfo.getStatus().name();
        }
    }

}
