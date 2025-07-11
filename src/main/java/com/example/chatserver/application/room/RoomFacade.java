package com.example.chatserver.application.room;

import com.example.chatserver.domain.room.Room;
import com.example.chatserver.domain.room.RoomService;
import com.example.chatserver.interfaces.room.RoomDto;
import com.example.chatserver.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomFacade {

    private final RoomService roomService;

    public RoomDto.OneToOneRoomResponse checkOrCreateOneToOneRoom(Long otherUserId) {
        Long currentUserId = getCurrentUserId();

        Room room = roomService.findOrCreateOneToOneRoom(currentUserId, otherUserId);
        return new RoomDto.OneToOneRoomResponse(room);
    }


    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((CustomUserDetails) authentication.getPrincipal()).getUser().getId();
    }

}
