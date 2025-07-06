package com.example.chatserver.infrastructure.profileimagefile;

import com.example.chatserver.domain.profileimagefile.ProfileImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageFileRepository extends JpaRepository<ProfileImageFile, Long> {
}
