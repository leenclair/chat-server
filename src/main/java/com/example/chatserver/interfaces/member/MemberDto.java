package com.example.chatserver.interfaces.member;

import com.example.chatserver.domain.member.Member;
import com.example.chatserver.domain.member.MemberCommand;
import com.example.chatserver.domain.member.MemberInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class MemberDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest {
        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Email is required")
        private String email;
        @NotBlank(message = "Password is required")
        private String password;

        public MemberCommand toCommand() {
            return MemberCommand.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class LoginRequest {
        @NotBlank(message = "Email is required")
        private String email;
        @NotBlank(message = "Password is required")
        private String password;

        public MemberCommand toCommand() {
            return MemberCommand.builder()
                    .email(email)
                    .password(password)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class CreateResponse {
        private final String memberToken;
        private final String name;
        private final String email;
        private final Member.Role role;

        public CreateResponse(MemberInfo memberInfo) {
            this.memberToken = memberInfo.getMemberToken();
            this.name = memberInfo.getName();
            this.email = memberInfo.getEmail();
            this.role = memberInfo.getRole();
        }
    }

}
