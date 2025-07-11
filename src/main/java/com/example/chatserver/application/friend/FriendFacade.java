package com.example.chatserver.application.friend;

import com.example.chatserver.domain.friend.Friend;
import com.example.chatserver.domain.friend.FriendService;
import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.profile.ProfileService;
import com.example.chatserver.domain.user.User;
import com.example.chatserver.domain.user.UserReader;
import com.example.chatserver.interfaces.friend.FriendDto;
import com.example.chatserver.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendFacade {

    private final FriendService friendService;
    private final ProfileService profileService;
    private final UserReader userReader;

    public List<FriendDto.Main> retrieveFriends(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        List<Friend> friends = friendService.findAllByReceiverId(user.getId());
        return friends.stream()
                .map(friend -> {
                    Profile profile = profileService.getProfileByUserId(friend.getRequester().getId());
                    String fileName = profile.getProfileImage().getProfileImageFile().getFileName();
                    return new FriendDto.Main(profile, fileName);
                })
                .toList();
    }

    public FriendDto.Main searchByEmail(String email) {
        User user = userReader.searchUser(email);
        if(user == null) return null;

        Profile profile = profileService.getProfileByUserId(user.getId());
        String fileName = profile.getProfileImage().getProfileImageFile().getFileName();
        return new FriendDto.Main(profile, fileName);
    }

    public void requestFriend(FriendDto.FriendRequest request) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User receiver = userDetails.getUser();
        User requester = userReader.getUserById(request.getFriendId());

        Friend initFriend = request.toEntity(receiver, requester);
        Friend friend = friendService.save(initFriend);
        log.info("save friends, result id: {}, Requester: {}, Friend: {}", requester.getId(), receiver.getId(), friend.getId());
    }

}
