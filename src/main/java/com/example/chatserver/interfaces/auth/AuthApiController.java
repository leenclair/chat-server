package com.example.chatserver.interfaces.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDto.LoginRequest loginRequest) {
        log.info("Login request received: {}", loginRequest);


        return ResponseEntity.ok("Login successful");

    }

}
