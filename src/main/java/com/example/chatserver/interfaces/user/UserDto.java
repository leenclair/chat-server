package com.example.chatserver.interfaces.user;

import com.example.chatserver.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserDto {

    @Getter
    @Setter
    @ToString
    public static class SignUpRequest {
        private String email;
        private String password;
        private String phoneNumber;

        public User toEntity(String passwordHashed) {
            return User.builder()
                    .email(email)
                    .password(passwordHashed)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class SignUpResponse {
        private final Long userId;
        private final String email;
        private final String role;

        public SignUpResponse(User user) {
            this.userId = user.getId();
            this.email = user.getEmail();
            this.role = "ROLE_USER"; // Default role
        }
    }



}
