package com.example.chatserver.interfaces.user;

import com.example.chatserver.application.user.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserFacade userFacade;

    @PostMapping("/api/v1/users")
    public ResponseEntity<?> createUser(@RequestBody UserDto.SignUpRequest request) {
        log.info("Create user request received: {}", request.getEmail());

        var response = userFacade.registerUser(request);

        return ResponseEntity.ok().body(response);
    }


}
