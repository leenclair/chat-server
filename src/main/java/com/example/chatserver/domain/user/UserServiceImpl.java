package com.example.chatserver.domain.user;

import com.example.chatserver.interfaces.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserStore userStore;
    private final UserReader userReader;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto.SignUpResponse registerUser(UserDto.SignUpRequest request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User initUser = request.toEntity(hashedPassword);
        User user = userStore.store(initUser);

        return new UserDto.SignUpResponse(user);
    }
}
