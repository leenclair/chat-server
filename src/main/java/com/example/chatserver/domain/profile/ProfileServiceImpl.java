package com.example.chatserver.domain.profile;

import com.example.chatserver.common.exception.EntityNotFoundException;
import com.example.chatserver.domain.profileimage.ProfileImage;
import com.example.chatserver.domain.user.User;
import com.example.chatserver.infrastructure.profile.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private static final List<String> names = List.of("이로", "링클", "제이스", "미니", "뚜뚜");
    private static final List<String> nicknames = List.of("귀여운이로", "멋진사자", "깡충토끼", "미니미니", "꼬꼬");
    private final ProfileStore profileStore;
    private final ProfileRepository profileRepository;

    @Override
    public Profile createProfile(User user, String phoneNumber) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("User must have a valid ID");
        }

        Profile profile = Profile.builder()
                .user(user)
                .name(names.get(new Random().nextInt(names.size())))
                .nickname(nicknames.get(new Random().nextInt(nicknames.size())))
                .phoneNumber(phoneNumber)
                .build();

        return profileStore.store(profile);
    }

    @Override
    public void updateProfileImage(Long profileId, ProfileImage profileImage) {
        profileRepository.updateProfileImageId(profileId, profileImage);
    }

    @Override
    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for user ID: " + userId));
    }
}
