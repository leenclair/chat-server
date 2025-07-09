package com.example.chatserver.interfaces.room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomApiController {

    /**
     * 1:1 채팅방 생성 API
     * 기존 친구 관계가 있는 유저들 간에 1:1 채팅방을 생성합니다.
     * 채팅방이 존재하지 않으면 새로 생성하고, 존재하면 해당 채팅방을 반환합니다.
    */
    @PostMapping("/one-to-one")
    public ResponseEntity<?> createOneToOneRoom() {

        return null;
    }

}
