package com.example.chatserver.domain.profileimagefile;

import com.example.chatserver.domain.user.User;
import com.example.chatserver.external.service.DicebearAvatarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileImageFileServiceImpl implements ProfileImageFileService {

    private final ProfileImageFileStore profileImageFileStore;
    private final DicebearAvatarService dicebearAvatarService;

    @Override
    public ProfileImageFile createProfileImageFile(User user) {
        var diceberAvatarResponse = dicebearAvatarService.generateAndSaveAvatar(user.getEmail());
        ProfileImageFile initProfileImageFile = ProfileImageFile.builder()
                .user(user)
                .fileName(diceberAvatarResponse.getFilename())
                .fileOrgName(diceberAvatarResponse.getFileOrgName())
                .fileSize(diceberAvatarResponse.getFileSize())
                .fileType(diceberAvatarResponse.getFileType())
                .build();

        return profileImageFileStore.store(initProfileImageFile);
    }


}
