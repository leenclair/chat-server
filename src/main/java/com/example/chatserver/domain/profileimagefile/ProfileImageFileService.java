package com.example.chatserver.domain.profileimagefile;

import com.example.chatserver.domain.user.User;

public interface ProfileImageFileService {
    ProfileImageFile createProfileImageFile(User user);
}
