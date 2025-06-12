package com.example.chatserver.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MemberCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterMemberRequest {
        private final String name;
        private final String email;
        private final String password;

        public Member toEntity(String encodedPassword) {
            return Member.builder()
                    .name(name)
                    .email(email)
                    .password(encodedPassword)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class AuthenticateRequest {
        private final String email;
        private final String password;


    }

}
