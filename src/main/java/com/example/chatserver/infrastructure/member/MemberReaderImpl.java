package com.example.chatserver.infrastructure.member;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.member.Member;
import com.example.chatserver.domain.member.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberReaderImpl implements MemberReader {
    private final MemberRepository memberRepository;

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
