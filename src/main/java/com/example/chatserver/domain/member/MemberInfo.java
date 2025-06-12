package com.example.chatserver.domain.member;

import lombok.Getter;
import lombok.ToString;

@Getter
public class MemberInfo {

    @Getter
    @ToString
    public static class Main {
        private final Long id;
        private final String memberToken;
        private final String name;
        private final String email;
        private final Member.Role role;

        public Main(Member member) {
            this.id = member.getId();
            this.memberToken = member.getMemberToken();
            this.name = member.getName();
            this.email = member.getEmail();
            this.role = member.getRole();
        }
    }

    @Getter
    @ToString
    public static class AuthenticateInfo {
        private final String email;
        private final String name;
        private final Member.Role role;

        public AuthenticateInfo(Member member) {
            this.email = member.getEmail();
            this.name = member.getName();
            this.role = member.getRole();
        }
    }

    @Getter
    @ToString
    public static class LoginInfo {
        private final String jwtToken;
        private final String name;
        private final String email;

        public LoginInfo(String jwtToken, String name, String email) {
            this.jwtToken = jwtToken;
            this.name = name;
            this.email = email;
        }
    }

}
