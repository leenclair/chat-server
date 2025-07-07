package com.example.chatserver.application.user;

import com.example.chatserver.common.exception.InvalidRequestException;
import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.profile.ProfileService;
import com.example.chatserver.domain.profileimage.ProfileImage;
import com.example.chatserver.domain.profileimage.ProfileImageService;
import com.example.chatserver.domain.profileimagefile.ProfileImageFile;
import com.example.chatserver.domain.profileimagefile.ProfileImageFileService;
import com.example.chatserver.domain.user.User;
import com.example.chatserver.domain.user.UserService;
import com.example.chatserver.interfaces.auth.AuthDto;
import com.example.chatserver.interfaces.user.UserDto;
import com.example.chatserver.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final ProfileService profileService;
    private final ProfileImageFileService profileImageFileService;
    private final ProfileImageService profileImageService;
    private final JwtProvider jwtProvider;

    @Transactional
    public UserDto.SignUpResponse registerUser(UserDto.SignUpRequest request) {
        try {
            // Call the user service to create the user
            User user =  userService.registerUser(request);
            // Create a profile for the user
            Profile profile = profileService.createProfile(user, request.getPhoneNumber());
            // Create a profile_image_file for the user
            ProfileImageFile profileImageFile = profileImageFileService.createProfileImageFile(user);
            // Create a profile image for the user
            ProfileImage profileImage = profileImageService.createProfileImage(user, profile, profileImageFile);
            profileService.updateProfileImage(profile.getId(), profileImage);

            return new UserDto.SignUpResponse(user);
        }catch (Exception e){
            log.error("User registration failed for email: {}", request.getEmail(), e);
            throw new InvalidRequestException("User registration failed for email: " + request.getEmail());
        }
    }

    public AuthDto.LoginResponse login(AuthDto.LoginRequest request) {
        // Call the user service to handle login
        User user = userService.login(request);
        String token = jwtProvider.createToken(user.getEmail());
        return new AuthDto.LoginResponse(user, token);

    }
}
