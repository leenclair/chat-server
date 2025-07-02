package com.example.chatserver.domain.readreceipt;

import com.example.chatserver.domain.AbstractEntity;
import com.example.chatserver.domain.message.Message;
import com.example.chatserver.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "read_receipts")
@Getter
@NoArgsConstructor
public class ReadReceipt extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readReceiptId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    private LocalDateTime readAt;
}
