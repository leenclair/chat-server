package com.example.chatserver.domain.profileimage;

import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.profileimagefile.ProfileImageFile;
import com.example.chatserver.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileImageServiceImpl implements ProfileImageService {
    private final ProfileImageStore profileImageStore;

    @Override
    public ProfileImage createProfileImage(User user,
                                           Profile profile,
                                           ProfileImageFile profileImageFile) {

        ProfileImage initProfileImage = ProfileImage.builder()
                .profile(profile)
                .user(user)
                .profileImageFile(profileImageFile)
                .build();
        return profileImageStore.store(initProfileImage);
    }
}
