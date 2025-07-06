package com.example.chatserver.domain.profileimage;

import com.example.chatserver.domain.AbstractEntity;
import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.profileimagefile.ProfileImageFile;
import com.example.chatserver.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile_images")
@Getter
@NoArgsConstructor
public class ProfileImage extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 프로필 주인

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_file_id", nullable = false)
    private ProfileImageFile profileImageFile;

    @Builder
    public ProfileImage(Profile profile,
                        User user,
                        ProfileImageFile profileImageFile) {
        this.profile = profile;
        this.user = user;
        this.profileImageFile = profileImageFile;
    }
}
