package com.example.chatserver.interfaces.chatroom;

import com.example.chatserver.domain.chatroom.ChatRoomInfo;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class ChatRoomDto {

    @Getter
    @ToString
    public  static class ChatRoomResponse {
        private final Long roomId;
        private final boolean isNewRoom;

        public ChatRoomResponse(ChatRoomInfo.PrivateChatRoomInfo chatRoomInfo) {
            this.roomId = chatRoomInfo.getRoomId();
            this.isNewRoom = chatRoomInfo.isNewRoom();
        }
    }

    @Getter
    @ToString
    public static class retrieveMessagesResponse {
        private final Long messageId;
        private final Long senderId;
        private final String senderNickName;
        private final String content;
        private final String sentAt;
        private final String status;
        private final boolean isMyMessage;
        private final Long readCount;

        public retrieveMessagesResponse(ChatRoomInfo.MessageResponse messageInfo, Long currentMemberId) {
            this.messageId = messageInfo.getMessageId();
            this.senderId = messageInfo.getSenderId();
            this.senderNickName = messageInfo.getSenderNickname();
            this.content = messageInfo.getContent();
            this.sentAt = messageInfo.getSentAt();
            this.status = messageInfo.getStatus();
            this.isMyMessage = currentMemberId.equals(messageInfo.getSenderId());
            this.readCount = messageInfo.getReadCount();
        }
    }

}
