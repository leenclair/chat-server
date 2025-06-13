package com.example.chatserver.domain.member;

import java.util.List;

public interface MemberService {
    MemberInfo.Main createMember(MemberCommand.RegisterMemberRequest command);
    MemberInfo.AuthenticateInfo authenticate(MemberCommand.AuthenticateRequest command);
    List<MemberInfo.Main> getMembers();
}
