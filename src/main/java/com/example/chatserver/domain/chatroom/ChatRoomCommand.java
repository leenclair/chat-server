package com.example.chatserver.domain.chatroom;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ChatRoomCommand {

    private final Long roomId;

}
