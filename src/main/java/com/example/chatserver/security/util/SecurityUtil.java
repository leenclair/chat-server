package com.example.chatserver.security.util;

import com.example.chatserver.domain.user.User;
import com.example.chatserver.security.CustomUserDetails;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new AccessDeniedException("인증되지 않은 사용자입니다.");
        }
        return ((CustomUserDetails) authentication.getPrincipal()).getUser();
    }

}
