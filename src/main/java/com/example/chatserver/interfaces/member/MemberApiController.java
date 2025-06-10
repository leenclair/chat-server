package com.example.chatserver.interfaces.member;

import com.example.chatserver.application.member.MemberFacade;
import com.example.chatserver.domain.member.MemberCommand;
import com.example.chatserver.domain.member.MemberInfo;
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
@RequestMapping("/api/v1/members")
public class MemberApiController {
    private final MemberFacade memberFacade;


    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberDto.RegisterRequest request) {
        MemberCommand command = request.toCommand();
        MemberInfo memberInfo = memberFacade.createMember(command);
        MemberDto.CreateResponse response = new MemberDto.CreateResponse(memberInfo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
