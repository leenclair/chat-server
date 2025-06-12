package com.example.chatserver.infrastructure.member;

import com.example.chatserver.domain.member.MemberInfo;
import com.example.chatserver.domain.member.TokenGenerator;
import com.example.chatserver.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenGeneratorImpl implements TokenGenerator {
    private final JwtProvider jwtProvider;

    @Override
    public String generateToken(MemberInfo.AuthenticateInfo info) {
        return jwtProvider.createToken(info.getEmail(), info.getRole().getDescription());
    }
}
