package com.example.chatserver.infrastructure;

import com.example.chatserver.domain.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationExecutor implements NotificationService {
    @Override
    public void sendEmail(String email, String subject, String message) {
        log.info("sendEmail called with email: {}, subject: {}, message: {}", email, subject, message);
    }
}
