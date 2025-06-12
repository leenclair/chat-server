package com.example.chatserver.domain.member;

public interface MemberReader {
    Member findByEmail(String email);
}
