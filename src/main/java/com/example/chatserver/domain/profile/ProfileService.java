package com.example.chatserver.domain.profile;

import com.example.chatserver.domain.profileimage.ProfileImage;
import com.example.chatserver.domain.user.User;

public interface ProfileService {
    Profile createProfile(User user, String phoneNumber);
    void updateProfileImage(Long profileId, ProfileImage profileImage);
    Profile getProfileByUserId(Long userId);
}
