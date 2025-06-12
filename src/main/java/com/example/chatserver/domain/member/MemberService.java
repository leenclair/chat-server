package com.example.chatserver.domain.member;

public interface MemberService {
    MemberInfo.Main createMember(MemberCommand.RegisterMemberRequest command);
    MemberInfo.AuthenticateInfo authenticate(MemberCommand.AuthenticateRequest command);
}
