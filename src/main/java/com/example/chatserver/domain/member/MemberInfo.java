package com.example.chatserver.domain.member;

import lombok.Getter;

@Getter
public class MemberInfo {
    private final Long id;
    private final String memberToken;
    private final String name;
    private final String email;
    private final Member.Role role;

    public MemberInfo(Member member) {
        this.id = member.getId();
        this.memberToken = member.getMemberToken();
        this.name = member.getName();
        this.email = member.getEmail();
        this.role = member.getRole();
    }
}
