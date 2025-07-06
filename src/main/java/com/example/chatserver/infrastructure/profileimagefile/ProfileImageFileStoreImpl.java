package com.example.chatserver.infrastructure.profileimagefile;

import com.example.chatserver.domain.profileimagefile.ProfileImageFile;
import com.example.chatserver.domain.profileimagefile.ProfileImageFileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileImageFileStoreImpl implements ProfileImageFileStore {
    private final ProfileImageFileRepository profileImageFileRepository;


    @Override
    public ProfileImageFile store(ProfileImageFile profileImageFile) {
        return profileImageFileRepository.save(profileImageFile);
    }
}
