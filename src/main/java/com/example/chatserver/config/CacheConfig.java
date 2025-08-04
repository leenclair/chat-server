package com.example.chatserver.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.setCacheNames(Arrays.asList(
                "friends",
                "rooms"
        ));

        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(1000) // 최대 1000개 항목
                .expireAfterWrite(30, TimeUnit.MINUTES) // 30분 후 만료
                );

        return cacheManager;
    }
}