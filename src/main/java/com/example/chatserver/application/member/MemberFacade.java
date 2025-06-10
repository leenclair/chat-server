package com.example.chatserver.application.member;

import com.example.chatserver.domain.member.MemberCommand;
import com.example.chatserver.domain.member.MemberInfo;
import com.example.chatserver.domain.member.MemberService;
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

    public MemberInfo createMember(MemberCommand memberCommand) {
        log.info("Creating member with command: {}", memberCommand);
        MemberInfo memberInfo = memberService.createMember(memberCommand);
        notificationService.sendEmail(memberInfo.getEmail(),
                "Welcome to ChatServer",
                "Hello " + memberInfo.getName() + ", welcome to ChatServer! Your member token is: " + memberInfo.getMemberToken());

        return memberInfo;
    }
}
