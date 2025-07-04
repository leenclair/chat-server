package com.example.chatserver.infrastructure.user;

import com.example.chatserver.common.exception.InvalidRequestException;
import com.example.chatserver.domain.user.User;
import com.example.chatserver.domain.user.UserStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {

    private final UserRepository userRepository;

    @Override
    public User store(User user) {
        if(StringUtils.isEmpty(user.getEmail())) throw new InvalidRequestException("Email cannot be empty");
        if(StringUtils.isEmpty(user.getPassword())) throw new InvalidRequestException("Password cannot be empty");

        return userRepository.save(user);
    }
}
