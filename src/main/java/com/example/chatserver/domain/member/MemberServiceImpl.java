package com.example.chatserver.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberStore memberStore;
    private final PasswordEncoder passwordEncoder;
    private final MemberReader memberReader;
    private final PasswordVerifier passwordVerifier;

    @Override
    @Transactional
    public MemberInfo.Main createMember(MemberCommand.RegisterMemberRequest command) {
        String password = command.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        Member initMember = command.toEntity(encodedPassword);
        Member member = memberStore.store(initMember);
        return new MemberInfo.Main(member);
    }

    @Override
    public MemberInfo.AuthenticateInfo authenticate(MemberCommand.AuthenticateRequest command) {
        Member member = memberReader.findByEmail(command.getEmail());
        passwordVerifier.verify(command.getPassword(), member.getPassword());

        return new MemberInfo.AuthenticateInfo(member);
    }
}
