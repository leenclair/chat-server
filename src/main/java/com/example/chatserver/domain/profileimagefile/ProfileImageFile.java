package com.example.chatserver.domain.profileimagefile;

import com.example.chatserver.domain.AbstractEntity;
import com.example.chatserver.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile_image_files")
@Getter
@NoArgsConstructor
public class ProfileImageFile extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 업로더(소유자)

    @Column(nullable = false)
    private String fileName;      // 저장 파일명

    @Column(nullable = false)
    private String fileOrgName;   // 원본 파일명

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private String fileType;      // 예: "image/png", "image/jpeg"

    @Builder
    public ProfileImageFile(User user,
                            String fileName,
                            String fileOrgName,
                            Long fileSize,
                            String fileType) {
        this.user = user;
        this.fileName = fileName;
        this.fileOrgName = fileOrgName;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }
}

