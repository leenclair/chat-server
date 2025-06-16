package com.example.chatserver.domain.chatroom;

import com.example.chatserver.domain.chatmessage.ChatMessage;
import lombok.Getter;
import lombok.ToString;

import java.time.format.DateTimeFormatter;

@Getter
public class ChatRoomInfo {

    @Getter
    @ToString
    public static class PrivateChatRoomInfo {
        private final Long roomId;
        private final boolean isNewRoom;

        public PrivateChatRoomInfo(Long roomId, boolean isNewRoom) {
            this.roomId = roomId;
            this.isNewRoom = isNewRoom;
        }

        public static PrivateChatRoomInfo existing(Long roomId) {
            return new PrivateChatRoomInfo(roomId, false);
        }

        public static PrivateChatRoomInfo newRoom(Long roomId) {
            return new PrivateChatRoomInfo(roomId, true);
        }
    }

    @Getter
    @ToString
    public  static class MessageResponse {
        private final Long messageId;
        private final Long senderId;
        private final String senderNickname;
        private final String content;
        private final String status;
        private final String sentAt;

        public MessageResponse(ChatMessage message) {
            this.messageId = message.getId();
            this.senderId = message.getSender().getId();
            this.senderNickname = message.getSender().getNickname();
            this.content = message.getContent();
            this.status = message.getStatus().toString();
            this.sentAt = message.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }

}
