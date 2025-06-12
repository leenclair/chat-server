package com.example.chatserver.domain.member;

import com.example.chatserver.common.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordVerifierImpl implements PasswordVerifier {
    private final PasswordEncoder passwordEncoder;

    @Override
    public void verify(String password, String encodedPassword) {
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new InvalidRequestException("Password does not match");
        }
    }
}
