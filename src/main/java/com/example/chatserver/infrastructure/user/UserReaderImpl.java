package com.example.chatserver.infrastructure.user;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.user.User;
import com.example.chatserver.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {

    private final UserRepository userRepository;

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
    }
}
