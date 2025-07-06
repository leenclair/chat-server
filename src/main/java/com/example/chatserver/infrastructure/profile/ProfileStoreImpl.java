package com.example.chatserver.infrastructure.profile;

import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.profile.ProfileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileStoreImpl implements ProfileStore {
    private final ProfileRepository profileRepository;

    @Override
    public Profile store(Profile profile) {
        if (profile.getUser() == null || profile.getUser().getId() == null) {
            throw new IllegalArgumentException("Profile must have a valid user");
        }
        return profileRepository.save(profile);
    }
}
