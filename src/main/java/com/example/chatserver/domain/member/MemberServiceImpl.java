package com.example.chatserver.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberStore memberStore;

    @Override
    @Transactional
    public MemberInfo createMember(MemberCommand memberCommand) {
        Member initMember = memberCommand.toEntity();
        Member member = memberStore.store(initMember);
        return new MemberInfo(member);
    }
}
