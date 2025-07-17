package com.example.chatserver.infrastructure.profile;

import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.profile.ProfileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProfileReaderImpl implements ProfileReader {
    private final ProfileRepository profileRepository;

    @Override
    public String getNickname(Long userId) {
        Optional<Profile> profile = profileRepository.findByUserId(userId);
        return profile.map(Profile::getNickname).orElse(null);
    }

    @Override
    public Optional<Profile> getProfile(Long userId) {
        return profileRepository.findByUserId(userId);
    }
}
