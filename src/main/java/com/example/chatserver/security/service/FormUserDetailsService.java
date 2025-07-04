package com.example.chatserver.security.service;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.user.User;
import com.example.chatserver.infrastructure.user.UserRepository;
import com.example.chatserver.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service("userDetailsService")
@RequiredArgsConstructor
public class FormUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("No user found with username: " + username);
        }
        log.info("Loading user by username: {}", username);

        return new CustomUserDetails(user.get());

    }
}
