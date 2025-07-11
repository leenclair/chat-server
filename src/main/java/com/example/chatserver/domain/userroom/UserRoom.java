package com.example.chatserver.domain.userroom;

import com.example.chatserver.domain.AbstractEntity;
import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_rooms")
@Getter
@NoArgsConstructor
public class UserRoom extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private Long notReadChat;

    private Long lastReadMessageId;

    @Column(nullable = false)
    private LocalDateTime joinedAt;

    private LocalDateTime leftAt;

    @Column(nullable = false)
    private boolean isLeft;

    @Builder
    public UserRoom(User user,
                    Room room,
                    Long notReadChat,
                    Long lastReadMessageId,
                    LocalDateTime joinedAt,
                    LocalDateTime leftAt,
                    boolean isLeft
    ) {
        this.user = user;
        this.room = room;
        this.notReadChat = notReadChat;
        this.lastReadMessageId = lastReadMessageId;
        this.joinedAt = joinedAt;
        this.leftAt = leftAt;
        this.isLeft = isLeft;
    }
}
