package com.example.chatserver.domain.user;

import com.example.chatserver.interfaces.user.UserDto;

public interface UserService {
    UserDto.SignUpResponse registerUser(UserDto.SignUpRequest request);
}
