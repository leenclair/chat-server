package com.example.chatserver.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MemberCommand {
    private final String name;
    private final String email;
    private final String password;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
