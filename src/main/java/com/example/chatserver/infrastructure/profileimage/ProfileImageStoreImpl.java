package com.example.chatserver.infrastructure.profileimage;

import com.example.chatserver.domain.profileimage.ProfileImage;
import com.example.chatserver.domain.profileimage.ProfileImageStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileImageStoreImpl implements ProfileImageStore {
    private final ProfileImageRepository profileImageRepository;

    @Override
    public ProfileImage store(ProfileImage profileImage) {
        return profileImageRepository.save(profileImage);
    }
}
