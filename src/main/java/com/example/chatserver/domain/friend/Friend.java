package com.example.chatserver.domain.friend;

import com.example.chatserver.domain.AbstractEntity;
import com.example.chatserver.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "friends")
@Getter
@NoArgsConstructor
public class Friend extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester; // 친구 신청한 사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver; // 친구 신청 받은 사람

    @Enumerated(EnumType.STRING)
    private Status status; // PENDING, ACCEPTED, REJECTED

    @Column(nullable = false)
    private LocalDateTime requestedAt;
    private LocalDateTime respondedAt;

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        PENDING("대기중"), ACCEPTED("승인됨"), REJECTED("거절됨");

        private final String description;
    }

    @Builder
    public Friend(User requester, User receiver) {
        this.requester = requester;
        this.receiver = receiver;
        this.status = Status.ACCEPTED;
        this.requestedAt = LocalDateTime.now();
        this.respondedAt = LocalDateTime.now();
    }
}

