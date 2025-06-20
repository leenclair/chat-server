package com.example.chatserver.domain.chatmessage;

import com.example.chatserver.domain.AbstractEntity;
import com.example.chatserver.domain.chatroom.ChatRoom;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.domain.readreceipt.ReadReceipt;
import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat_messages")
public class ChatMessage extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member sender;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageStatus status;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReadReceipt> readReceipts = Lists.newArrayList();

    public enum MessageStatus {
        SENDING,    // 전송 중
        SENT,       // 전송 완료
        DELIVERED,  // 전달됨
        READ,       // 읽음
        FAILED      // 전송 실패
    }

    @Builder
    public ChatMessage(ChatRoom room, Member sender, String content) {
        this.room = room;
        this.sender = sender;
        this.content = content;
        this.status = MessageStatus.SENDING;
    }

//    public void addReadReceipt(ReadReceipt readReceipt) {
//        this.readReceipts.add(readReceipt);
//        readReceipt.sendMessage(this);
//    }
//
//    public void updateStatus(MessageStatus status) {
//        this.status = status;
//    }
}
