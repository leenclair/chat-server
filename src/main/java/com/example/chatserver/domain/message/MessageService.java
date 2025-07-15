package com.example.chatserver.domain.message;

import com.example.chatserver.interfaces.message.MessageDto;
import com.example.chatserver.interfaces.stomp.StompMessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto.Main> getMessagesByRoomId(Long currentUserId, Long roomId);
    void saveMessage(Message message);


}
