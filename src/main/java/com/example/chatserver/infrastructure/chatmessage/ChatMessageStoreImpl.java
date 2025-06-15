package com.example.chatserver.infrastructure.chatmessage;

import com.example.chatserver.domain.chatmessage.ChatMessage;
import com.example.chatserver.domain.chatmessage.ChatMessageCommand;
import com.example.chatserver.domain.chatmessage.ChatMessageStore;
import com.example.chatserver.domain.readreceipt.ReadReceipt;
import com.example.chatserver.infrastructure.chatmember.ChatMemberRepository;
import com.example.chatserver.infrastructure.readreceipt.ReadReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatMessageStoreImpl implements ChatMessageStore {
    private final ChatMessageRepository chatMessageRepository;
    private final ReadReceiptRepository readReceiptRepository;
    private final ChatMemberRepository chatMemberRepository;

    @Transactional
    public ChatMessage saveMessage(ChatMessageCommand.RegisterMessage command) {
        // 1. 메시지 저장
        ChatMessage message = command.toEntity();

        ChatMessage savedMessage = chatMessageRepository.save(message);

        // 3. 읽음 상태 초기화
        initializeReadReceipts(savedMessage);

        return savedMessage;
    }

    private void initializeReadReceipts(ChatMessage message) {
        List<ReadReceipt> readReceipts = chatMemberRepository.findActiveMembersByRoomId(message.getRoomId())
                .stream()
                .map(member -> ReadReceipt.builder()
                        .message(message)
                        .userId(member.getUserId())
                        .build())
                .toList();

        readReceiptRepository.saveAll(readReceipts);
    }
}
