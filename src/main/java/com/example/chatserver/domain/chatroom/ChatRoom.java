package com.example.chatserver.domain.chatroom;

import com.example.chatserver.domain.AbstractEntity;
import com.example.chatserver.domain.chatmember.ChatMember;
import com.example.chatserver.domain.chatmessage.ChatMessage;
import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "chat_rooms")
public class ChatRoom extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<ChatMember> members = Lists.newArrayList();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<ChatMessage> messages = Lists.newArrayList();

    @Builder
    public ChatRoom(RoomType type, String name) {
        this.type = type;
        this.name = name;
    }

    public void addMember(ChatMember member) {
        this.members.add(member);
        member.enterRoom(this);
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }

    public enum RoomType {
        DIRECT,     // 1:1 채팅
        GROUP,      // 그룹 채팅
        CHANNEL     // 채널
    }
}
