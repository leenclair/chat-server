package com.example.chatserver.domain.member;

import com.example.chatserver.common.exception.InvalidRequestException;
import com.example.chatserver.common.util.TokenGenerator;
import com.example.chatserver.domain.chatmember.ChatMember;
import com.example.chatserver.domain.chatmessage.ChatMessage;
import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "members")
public class Member {
    private static final String MEMBER_PREFIX = "member_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberToken;
    private String name;
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "sender")
    private List<ChatMessage> sentMessages = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.PERSIST)
//    private List<ChatMember> chatMembers = Lists.newArrayList();

    @Getter
    @RequiredArgsConstructor
    public enum Role {
        ADMIN("ADMIN"), USER("USER");
        private final String description;
    }

    @Builder
    public Member(String memberToken, String name, String email, String password) {
        if(StringUtils.isBlank(name)) throw new InvalidRequestException("empty name");
        if(StringUtils.isBlank(email)) throw new InvalidRequestException("empty email");
        if(StringUtils.isBlank(password)) throw new InvalidRequestException("empty password");

        this.memberToken = TokenGenerator.randomCharacterWithPrefix(MEMBER_PREFIX);
        this.name = name;
        this.nickname = UUID.randomUUID().toString().substring(0, 8); // Generate a random nickname
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }
}
