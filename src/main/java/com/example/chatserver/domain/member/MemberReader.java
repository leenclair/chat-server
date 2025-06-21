package com.example.chatserver.domain.member;

import java.util.List;

public interface MemberReader {
    Member findByEmail(String email);
    List<Member> findAll();
    Member findByMemberToken(String token);
    Member findById(Long id);
}
