package com.example.chatserver.infrastructure;

import com.example.chatserver.domain.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationExecutor implements NotificationService {
    //Todo: 유저 생성 후 이메일 발송 기능 구현
    @Override
    public void sendEmail(String email, String subject, String message) {
        log.info("Sending email to {}", email);
    }
}
