package com.example.chatserver.domain.message;

import com.example.chatserver.domain.AbstractEntity;
import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Getter
@NoArgsConstructor
public class Message extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(length = 1000, nullable = false)
    private String message;

    @Builder
    public Message(User sender, Room room, String message) {
        this.sender = sender;
        this.room = room;
        this.message = message;
    }
}

