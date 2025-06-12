package com.example.chatserver.domain.member;

public interface TokenGenerator {
    String generateToken(MemberInfo.AuthenticateInfo info);
}
