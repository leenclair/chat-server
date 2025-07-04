package com.example.chatserver.interfaces.auth;

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

}
