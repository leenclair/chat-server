package com.example.chatserver.infrastructure.profileimage;

import com.example.chatserver.domain.profileimage.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
}
