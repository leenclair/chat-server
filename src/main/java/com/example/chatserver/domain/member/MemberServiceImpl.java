package com.example.chatserver.domain.member;

import com.example.chatserver.infrastructure.member.MemberRepository;
import com.example.chatserver.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberStore memberStore;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public MemberInfo createMember(MemberCommand memberCommand) {
        String password = memberCommand.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        Member initMember = memberCommand.toEntity(encodedPassword);
        Member member = memberStore.store(initMember);
        return new MemberInfo(member);
    }

    @Override
    public String login(MemberCommand memberCommand) {
        Member member = memberRepository.findByEmail(memberCommand.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        // 기존 비밀번호 검증
        if (!passwordEncoder.matches(memberCommand.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("현재 비밀번호가 일치하지 않습니다.");
        }

        return jwtProvider.createToken(memberCommand.getEmail(), "ROLE_USER");
    }
}
