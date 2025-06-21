package com.example.chatserver.infrastructure.chatmessage;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.chatmessage.ChatMessage;
import com.example.chatserver.domain.chatmessage.ChatMessageCommand;
import com.example.chatserver.domain.chatmessage.ChatMessageStore;
import com.example.chatserver.domain.chatroom.ChatRoom;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.domain.readreceipt.ReadReceipt;
import com.example.chatserver.infrastructure.chatmember.ChatMemberRepository;
import com.example.chatserver.infrastructure.chatroom.ChatRoomRepository;
import com.example.chatserver.infrastructure.member.MemberRepository;
import com.example.chatserver.infrastructure.readreceipt.ReadReceiptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatMessageStoreImpl implements ChatMessageStore {
    private final ChatMessageRepository chatMessageRepository;
    private final ReadReceiptRepository readReceiptRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatMessage saveMessage(ChatMessageCommand.RegisterMessage command) {
        // 1. 메시지 저장
        Member sender = memberRepository.findByEmail(command.getSenderEmail())
                .orElseThrow(() -> new EntityNotFoundException("Sender not found with Email: " + command.getSenderEmail()));

        ChatRoom chatRoom = chatRoomRepository.findById(command.getRoomId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Room not found with ID: " + command.getRoomId()));
        ChatMessage message = command.toEntity(sender, chatRoom);

        ChatMessage savedMessage = chatMessageRepository.save(message);

        // 3. 읽음 상태 초기화
        initializeReadReceipts(savedMessage);

        return savedMessage;
    }

    private void initializeReadReceipts(ChatMessage message) {
        List<ReadReceipt> readReceipts = chatMemberRepository.findActiveMembersByRoomId(message.getRoom().getId())
                .stream()
                .map(member -> ReadReceipt.builder()
                        .message(message)
                        .userId(member.getMember().getId())
                        .build())
                .toList();

        readReceiptRepository.saveAll(readReceipts);
    }
}
