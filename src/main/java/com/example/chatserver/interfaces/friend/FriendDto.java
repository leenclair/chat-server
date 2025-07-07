package com.example.chatserver.interfaces.friend;

import com.example.chatserver.domain.friend.Friend;
import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class FriendDto {

    @Getter
    @Setter
    @ToString
    public static class FriendRequest {
        private Long friendId;

        public Friend toEntity(User receiver, User requester){
            return Friend.builder()
                    .requester(requester)
                    .receiver(receiver)
                    .build();

        }
    }

    @Getter
    @ToString
    public static class Main {
        private final Long friendId;
        private final String nickname;
        private final String profileImageUrl;

        public Main(Profile profile, String filename) {
            this.friendId = profile.getUser().getId();
            this.nickname = profile.getNickname();
            this.profileImageUrl = "http://localhost/api/v1/profile/image/" + filename;
        }
    }

}
