package com.example.chatserver.domain.message;

import com.example.chatserver.interfaces.message.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto.Main> getMessagesByRoomId(Long currentUserId, Long roomId);



}
