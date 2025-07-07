package com.example.chatserver.infrastructure.profile;

import com.example.chatserver.domain.profile.Profile;
import com.example.chatserver.domain.profileimage.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    @Modifying
    @Query("UPDATE Profile p SET p.profileImage = :profileImage " +
            "WHERE p.id = :profileId")
    void updateProfileImageId(@Param("profileId") Long profileId, @Param("profileImage") ProfileImage profileImage);

    Optional<Profile> findByUserId(Long userId);
}
