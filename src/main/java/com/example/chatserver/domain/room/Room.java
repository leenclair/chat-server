package com.example.chatserver.domain.room;

import com.example.chatserver.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "rooms")
@Getter
@NoArgsConstructor
public class Room extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type; // 예: "ONE_TO_ONE", "GROUP"

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ACTIVE("활성화"), WITHDRAWN("비활성화");

        private final String description;
    }

    @Builder
    public Room(String name, String type, Status status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }
}
