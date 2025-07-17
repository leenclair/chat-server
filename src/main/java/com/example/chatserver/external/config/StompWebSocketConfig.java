package com.example.chatserver.external.config;

import com.example.chatserver.security.handler.StompHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final StompHandler stompHandler;
    private final String allowedOrigins;

    public StompWebSocketConfig(StompHandler stompHandler,
                                @Value("${websocket.allowed-origins}") String allowedOrigins) {
        System.out.println("StompWebSocketConfig initialized with allowedOrigins: " + allowedOrigins);
        this.stompHandler = stompHandler;
        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connect")
                .setAllowedOrigins(allowedOrigins)
                .setAllowedOriginPatterns("*") // 모든 출처 허용
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지를 서버로 보낼 때 prefix
        registry.setApplicationDestinationPrefixes("/pub");

        // 메시지를 브라우저로 보내줄 prefix
        registry.enableSimpleBroker("/sub")
                .setHeartbeatValue(new long[]{10000, 10000}) // 10초마다 heartbeat
                .setTaskScheduler(heartBeatScheduler());
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);
    }

    @Bean
    public ThreadPoolTaskScheduler heartBeatScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix("wss-heartbeat-thread-");
        scheduler.initialize();
        return scheduler;
    }

}