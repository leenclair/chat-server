package com.example.chatserver.interfaces.friend;

import com.example.chatserver.application.friend.FriendFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
public class FriendApiController {

    private final FriendFacade friendFacade;

    @GetMapping
    public ResponseEntity<?> getFriends(){

        List<FriendDto.Main> response = friendFacade.retrieveFriends();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFriends(@RequestParam("email") String email){

        var response = friendFacade.searchByEmail(email);
        if(response != null) {
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestFriend(@RequestBody FriendDto.FriendRequest request){
        friendFacade.requestFriend(request);

        return ResponseEntity.ok().build();
    }

}
