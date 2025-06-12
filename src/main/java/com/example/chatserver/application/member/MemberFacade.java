package com.example.chatserver.application.member;

import com.example.chatserver.domain.member.MemberCommand;
import com.example.chatserver.domain.member.MemberInfo;
import com.example.chatserver.domain.member.MemberService;
import com.example.chatserver.domain.member.TokenGenerator;
import com.example.chatserver.domain.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberService memberService;
    private final NotificationService notificationService;
    private final TokenGenerator tokenGenerator;

    public MemberInfo.Main createMember(MemberCommand.RegisterMemberRequest command) {
        log.info("Creating member with command: {}", command);
        MemberInfo.Main info = memberService.createMember(command);
        notificationService.sendEmail(info.getEmail(),
                "Welcome to ChatServer",
                "Hello " + info.getName() + ", welcome to ChatServer!");

        return info;
    }

    public MemberInfo.LoginInfo login(MemberCommand.AuthenticateRequest command) {
        log.info("Authenticating member with command: {}", command);
        MemberInfo.AuthenticateInfo info = memberService.authenticate(command);
        String jwtToken = tokenGenerator.generateToken(info);

        return new MemberInfo.LoginInfo(jwtToken, info.getName(), info.getEmail());
    }



}
