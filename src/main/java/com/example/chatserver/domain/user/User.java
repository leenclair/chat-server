package com.example.chatserver.domain.user;

import com.example.chatserver.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status; // Enum 추천: ACTIVE, WITHDRAWN 등


    @Getter
    @RequiredArgsConstructor
    public enum Status{
        ACTIVE("활성화"), WITHDRAWN("비활성화");

        private final String description;
    }
    // 프로필/설정 등 단방향 매핑 추천
}

