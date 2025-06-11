package com.example.chatserver.domain.member;

public interface MemberService {
    MemberInfo createMember(MemberCommand memberCommand);
    String login(MemberCommand memberCommand);
}
