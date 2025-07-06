package com.example.chatserver.external.dto;

import com.example.chatserver.common.util.FileUtils;
import lombok.Getter;
import lombok.ToString;

import java.io.File;

public class AvatarDto {

    @Getter
    @ToString
    public static class DiceberAvatarResponse {
        private final String filename;
        private final String fileOrgName;
        private final Long fileSize;
        private final String fileType;

        public DiceberAvatarResponse(File file, String fileOrgName) {
            this.filename = file.getName();
            this.fileOrgName = fileOrgName;
            this.fileSize = FileUtils.getFileSize(file);
            this.fileType = "image/svg+xml"; // MIME 타입은 SVG로 고정
        }
    }

}
