package com.example.chatserver.domain.profileimage;

import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.profileimagefile.ProfileImageFile;
import com.example.chatserver.domain.user.User;

public interface ProfileImageService {
    ProfileImage createProfileImage(User user,
                                    Profile profile,
                                    ProfileImageFile profileImageFile);
}
