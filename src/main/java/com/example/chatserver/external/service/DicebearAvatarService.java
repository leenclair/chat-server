package com.example.chatserver.external.service;

import com.example.chatserver.common.util.FileUtils;
import com.example.chatserver.external.dto.AvatarDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Slf4j
@Service
public class DicebearAvatarService {

    private static final String DICEBEAR_API_URL = "https://api.dicebear.com/7.x/avataaars/svg";

    @Value("${config-value.profile-image-path}")
    private String PROFILE_IMAGES_SAVE_PATH;

    public AvatarDto.DiceberAvatarResponse generateAndSaveAvatar(String email) {
        try {
            // Dicebear API 호출
            String avatarUrl = buildAvatarUrl(email);
            byte[] avatarData = downloadAvatar(avatarUrl);

            // 파일명 생성 (email_timestamp.svg)
//            String fileName = email + "_" + System.currentTimeMillis() + ".svg";
            String filePath = PROFILE_IMAGES_SAVE_PATH + email;

            // 파일 저장
            FileUtils.createDirectory(filePath);
            File file = new File(filePath + File.separator + email + "_" + System.currentTimeMillis() + ".svg");
            FileUtils.writeByteArrayToFile(file, avatarData);

            String fileOrgName = email + "_avatar.svg";

            log.info("[INFO] Avatar saved: {}", filePath);

            return new AvatarDto.DiceberAvatarResponse(file, fileOrgName);

        } catch (Exception e) {
            log.error("Failed to generate avatar for user: {}", email, e);
            throw new RuntimeException("아바타 생성 실패", e);
        }
    }

    private String buildAvatarUrl(String seed) {
        return DICEBEAR_API_URL + "?seed=" + seed;
    }

    private byte[] downloadAvatar(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        return response.getBody();
    }

}
