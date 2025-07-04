package com.example.chatserver.application.user;

import com.example.chatserver.domain.notification.NotificationService;
import com.example.chatserver.domain.user.UserService;
import com.example.chatserver.interfaces.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final NotificationService notificationService;

    public UserDto.SignUpResponse registerUser(UserDto.SignUpRequest request) {
        // Call the user service to create the user
        UserDto.SignUpResponse response =  userService.registerUser(request);

        // Send a welcome email
        notificationService.sendEmail(
            request.getEmail(),
            "Welcome to ChatServer",
            "Thank you for registering with ChatServer!"
        );

        return response;
    }
}
