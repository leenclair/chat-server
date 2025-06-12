package com.example.chatserver.domain.member;

public interface PasswordVerifier {
    void verify(String password, String encodedPassword);
}
