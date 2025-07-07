package com.example.chatserver.domain.user;

import com.example.chatserver.interfaces.auth.AuthDto;
import com.example.chatserver.interfaces.user.UserDto;

import java.util.Optional;

public interface UserService {
    User registerUser(UserDto.SignUpRequest request);
    User login(AuthDto.LoginRequest request);
    Optional<User> getUserByEmail(String email);
}
