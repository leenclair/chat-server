package com.example.chatserver.interfaces.room;

import com.example.chatserver.domain.room.Room;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class RoomDto {

    @Getter
    @Setter
    @ToString
    public static class OneToOneRoomRequest {
        private Long friendId;
    }

    @Getter
    @ToString
    public static class OneToOneRoomResponse {
        private final Long roomId;
        private final String roomName;
        private final String type;

        public OneToOneRoomResponse(Room room) {
            this.roomId = room.getId();
            this.roomName = room.getName();
            this.type = room.getType();
        }

    }

}
