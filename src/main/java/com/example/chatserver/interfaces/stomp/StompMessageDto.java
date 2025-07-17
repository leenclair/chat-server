package com.example.chatserver.interfaces.stomp;

import com.example.chatserver.domain.message.Message;
import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class StompMessageDto {
    private Long roomId;
    private String senderEmail;
    private String message;

    public Message toEntity(Room room, User sender) {
        return Message.builder()
                .room(room)
                .message(message)
                .sender(sender)
                .build();
    }
}
