package com.example.chatserver.interfaces.member;

import com.example.chatserver.domain.member.Member;
import com.example.chatserver.domain.member.MemberCommand;
import com.example.chatserver.domain.member.MemberInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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

        public MemberCommand.RegisterMemberRequest toCommand() {
            return MemberCommand.RegisterMemberRequest.builder()
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

        public MemberCommand.AuthenticateRequest toCommand() {
            return MemberCommand.AuthenticateRequest.builder()
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

        public CreateResponse(MemberInfo.Main memberInfo) {
            this.memberToken = memberInfo.getMemberToken();
            this.name = memberInfo.getName();
            this.email = memberInfo.getEmail();
            this.role = memberInfo.getRole();
        }
    }

    @Getter
    @ToString
    public static class LoginResponse {
        private final String jwtToken;
        private final String name;
        private final String email;

        public LoginResponse(MemberInfo.LoginInfo info) {
            this.jwtToken = info.getJwtToken();
            this.name = info.getName();
            this.email = info.getEmail();
        }
    }

    @Getter
    @ToString
    public static class RetrieveMemberResponse {
        private final Long id;
        private final String memberToken;
        private final String name;
        private final String email;

        public RetrieveMemberResponse(MemberInfo.Main memberInfo) {
            this.id = memberInfo.getId();
            this.memberToken = memberInfo.getMemberToken();
            this.name = memberInfo.getName();
            this.email = memberInfo.getEmail();
        }

        public static List<RetrieveMemberResponse> listOf(List<MemberInfo.Main> memberInfos) {
            return memberInfos.stream()
                    .map(RetrieveMemberResponse::new)
                    .toList();
        }
    }

}
