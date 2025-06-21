package com.example.chatserver.domain.chatmember;

import com.example.chatserver.domain.chatroom.ChatRoom;
import com.example.chatserver.domain.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "chat_members")
public class ChatMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private ZonedDateTime joinAt;

    private ZonedDateTime leaveAt;

    @Column(nullable = false)
    private boolean kicked;

    @Column(nullable = false)
    private boolean alarmOn;

    @Builder
    public ChatMember(Member member, ChatRoom room) {
        this.member = member;
        this.room = room;
        this.joinAt = ZonedDateTime.now();
        this.kicked = false;
        this.alarmOn = true;
    }

    public void enterRoom(ChatRoom room) {
        this.room = room;
    }

    public void leave() {
        this.leaveAt = ZonedDateTime.now();
    }

    public void kick() {
        this.kicked = true;
        this.leaveAt = ZonedDateTime.now();
    }

    public void toggleAlarm() {
        this.alarmOn = !this.alarmOn;
    }
}
