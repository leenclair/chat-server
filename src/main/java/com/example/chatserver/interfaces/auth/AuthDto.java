package com.example.chatserver.interfaces.auth;

import com.example.chatserver.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class AuthDto {

    @Getter
    @Setter
    @ToString
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Getter
    @ToString
    public static class LoginResponse {
        private final String email;
        private final String role;
        private final String token;

        public LoginResponse(User user, String token) {
            this.email = user.getEmail();
            this.role = "ROLE_USER"; // Default role
            this.token = token;
        }
    }

}
