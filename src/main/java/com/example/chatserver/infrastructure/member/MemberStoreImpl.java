package com.example.chatserver.infrastructure.member;

import com.example.chatserver.common.exception.InvalidRequestException;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.domain.member.MemberStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberStoreImpl implements MemberStore {

    private final MemberRepository memberRepository;

    @Override
    public Member store(Member member) {
        if(StringUtils.isEmpty(member.getMemberToken())) throw new InvalidRequestException("member.token is empty");
        if(StringUtils.isEmpty(member.getName())) throw new InvalidRequestException("member.name is empty");
        if(StringUtils.isEmpty(member.getEmail())) throw new InvalidRequestException("member.email is empty");
        if(StringUtils.isEmpty(member.getPassword())) throw new InvalidRequestException("member.password is empty");
        if(memberRepository.existsByEmail(member.getEmail())) {
            throw new InvalidRequestException("member.email already exists");
        }

        return memberRepository.save(member);
    }
}
