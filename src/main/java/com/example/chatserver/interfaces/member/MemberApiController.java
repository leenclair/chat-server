package com.example.chatserver.interfaces.member;

import com.example.chatserver.application.member.MemberFacade;
import com.example.chatserver.domain.member.MemberCommand;
import com.example.chatserver.domain.member.MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApiController {
    private final MemberFacade memberFacade;

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberDto.RegisterRequest request) {
        log.info("Received request to create member: {}", request.getEmail());
        var command = request.toCommand();
        var info = memberFacade.createMember(command);
        var response = new MemberDto.CreateResponse(info);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getMembers() {
        log.info("Received request to get all members");
        var info = memberFacade.getMembers();
        var response = MemberDto.RetrieveMemberResponse.listOf(info);
        for (MemberDto.RetrieveMemberResponse retrieveMemberResponse : response) {
            log.info("Member Info: {}", retrieveMemberResponse);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
