package com.example.chatserver.domain.profile;

import com.example.chatserver.domain.AbstractEntity;
import com.example.chatserver.domain.profileimage.ProfileImage;
import com.example.chatserver.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profiles")
@Getter
@NoArgsConstructor
public class Profile extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_id")
    private ProfileImage profileImage; // 대표 이미지 (nullable)

    @Builder
    public Profile(User user,
                   String name,
                   String nickname,
                   String phoneNumber,
                   ProfileImage profileImage
    ) {
        this.user = user;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public void updateProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}

