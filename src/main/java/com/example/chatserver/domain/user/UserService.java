package com.example.chatserver.domain.user;

import com.example.chatserver.interfaces.auth.AuthDto;
import com.example.chatserver.interfaces.user.UserDto;

public interface UserService {
    User registerUser(UserDto.SignUpRequest request);
    User login(AuthDto.LoginRequest request);
}
