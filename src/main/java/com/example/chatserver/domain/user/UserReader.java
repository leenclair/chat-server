package com.example.chatserver.domain.user;

public interface UserReader {
    User getUser(String email);
    User searchUser(String email);
    User getUserById(Long userId);
}
