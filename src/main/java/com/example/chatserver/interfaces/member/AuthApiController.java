package com.example.chatserver.interfaces.member;

import com.example.chatserver.domain.member.MemberCommand;
import com.example.chatserver.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApiController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto.LoginRequest request) {;
        MemberCommand command = request.toCommand();
        String token = memberService.login(command);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
