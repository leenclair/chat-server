package com.example.chatserver.domain.user;

import com.example.chatserver.common.exception.InvalidRequestException;
import com.example.chatserver.interfaces.auth.AuthDto;
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
    public User registerUser(UserDto.SignUpRequest request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User initUser = request.toEntity(hashedPassword);
        return userStore.store(initUser);
    }

    @Override
    public User login(AuthDto.LoginRequest request) {
        User user = userReader.getUser(request.getEmail());
        // Check if the password matches the stored hashed password
        if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return user;
        }else {
            throw new InvalidRequestException("Invalid email or password");
        }
    }
}
