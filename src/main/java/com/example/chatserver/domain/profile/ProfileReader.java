package com.example.chatserver.domain.profile;

import java.util.Optional;

public interface ProfileReader {
    String getNickname(Long userId);
    Optional<Profile> getProfile(Long userId);
}
