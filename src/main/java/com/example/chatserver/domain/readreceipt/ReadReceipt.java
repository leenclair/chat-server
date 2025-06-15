package com.example.chatserver.domain.readreceipt;

import com.example.chatserver.domain.AbstractEntity;
import com.example.chatserver.domain.chatmessage.ChatMessage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "read_receipts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadReceipt extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private ChatMessage message;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private ZonedDateTime readAt;

    public void readReceipt(Long userId) {
        this.userId = userId;
        this.readAt = ZonedDateTime.now();
    }

    public void sendMessage(ChatMessage chatMessage) {
        this.message = chatMessage;
    }

    @Builder
    public ReadReceipt(Long userId, ChatMessage message) {
        this.userId = userId;
        this.message = message;
        this.readAt = ZonedDateTime.now();
    }
}
