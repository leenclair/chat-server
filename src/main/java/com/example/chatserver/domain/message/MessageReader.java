package com.example.chatserver.domain.message;

import java.util.List;

public interface MessageReader {

    List<Message> getMessagesByRoomId(Long roomId);
}
