package com.example.chatserver.interfaces.message;

import com.example.chatserver.domain.message.Message;
import com.example.chatserver.domain.profile.Profile;
import lombok.Getter;
import lombok.ToString;

public class MessageDto {

    @Getter
    @ToString
    public static class Main {
        private final Long messageId;
        private final String profileImageUrl;
        private final boolean isMine;
        private final Long userId;
        private final String nickname;
        private final String message;
        private final int notReadCount;

        public Main(Long currentUserId,
                    Message message,
                    Profile profile,
                    int notReadCount) {
            this.isMine = currentUserId.equals(message.getSender().getId());
            this.messageId = message.getId();
            this.profileImageUrl = "http://localhost/api/v1/profile/image/"
                    + profile.getProfileImage().getProfileImageFile().getFileOrgName();
            this.userId = message.getSender().getId();
            this.nickname = profile.getNickname();
            this.message = message.getMessage();
            this.notReadCount = notReadCount;
        }
    }

}
