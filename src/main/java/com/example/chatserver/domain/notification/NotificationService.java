package com.example.chatserver.domain.notification;

public interface NotificationService {
    void sendEmail(String email, String subject, String message);
}
