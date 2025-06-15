package com.example.chatserver.domain.member;

import com.example.chatserver.common.exception.InvalidRequestException;
import com.example.chatserver.common.util.TokenGenerator;
import com.example.chatserver.domain.chatmember.ChatMember;
import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

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

    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<ChatMember> chatMembers = Lists.newArrayList();

    @Getter
    @RequiredArgsConstructor
    public enum Role {
        ADMIN("ADMIN"), USER("USER");
        private final String description;
    }

    @Builder
    public Member(String name, String email, String password) {
        if(StringUtils.isBlank(name)) throw new InvalidRequestException("empty name");
        if(StringUtils.isBlank(email)) throw new InvalidRequestException("empty email");
        if(StringUtils.isBlank(password)) throw new InvalidRequestException("empty password");

        this.memberToken = TokenGenerator.randomCharacterWithPrefix(MEMBER_PREFIX);
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }
}
