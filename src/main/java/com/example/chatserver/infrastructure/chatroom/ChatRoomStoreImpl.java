package com.example.chatserver.infrastructure.chatroom;

import com.example.chatserver.domain.chatmember.ChatMember;
import com.example.chatserver.domain.chatroom.ChatRoom;
import com.example.chatserver.domain.chatroom.ChatRoomStore;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.infrastructure.chatmember.ChatMemberRepository;
import com.example.chatserver.infrastructure.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatRoomStoreImpl implements ChatRoomStore {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ChatRoom createPrivateChatRoom(Member member1, Member member2) {
        ChatMember chatMember1 = ChatMember.builder()
                .member(member1)
                .build();
        ChatMember chatMember2 = ChatMember.builder()
                .member(member2)
                .build();

        // 1. 새로운 1:1 채팅방 생성
        ChatRoom room = ChatRoom.builder()
                .type(ChatRoom.RoomType.DIRECT)
                .name("Private Chat")  // 필요에 따라 이름 설정
                .build();

        ChatRoom savedRoom = chatRoomRepository.save(room);

        // 2. 두 멤버를 채팅방에 추가
        chatMember1.enterRoom(savedRoom);
        chatMember2.enterRoom(savedRoom);
        List<ChatMember> members = Arrays.asList(chatMember1, chatMember2);

        chatMemberRepository.saveAll(members);

        return savedRoom;
    }

    private String getMemberNickname(Long memberId) {
        // 멤버 닉네임 조회 로직
        return memberRepository.findById(memberId)
                .map(Member::getNickname)
                .orElse("Unknown");
    }
}
