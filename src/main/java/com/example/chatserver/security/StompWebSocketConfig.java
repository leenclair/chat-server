package com.example.chatserver.security;

import com.example.chatserver.security.handler.StompHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
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
        // 메시지 발행 prefix 설정
        registry.setApplicationDestinationPrefixes("/publish");

        // 메시지 구독 prefix 설정
        registry.enableSimpleBroker("/topic");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);
    }

//    @Override
//    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
//        registration.setMessageSizeLimit(64 * 1024) // 64KB
//                .setSendBufferSizeLimit(512 * 1024) // 512KB
//                .setSendTimeLimit(20 * 1000); // 20 seconds
//    }
//
//    @Bean
//    public TaskScheduler heartbeatScheduler() {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(1);
//        scheduler.setThreadNamePrefix("ws-heartbeat-scheduler-");
//        scheduler.initialize();
//        return scheduler;
//    }
//
//    @Override
//    public void configureClientOutboundChannel(ChannelRegistration registration) {
//        registration.taskExecutor()
//                .corePoolSize(4)
//                .maxPoolSize(8)
//                .queueCapacity(100);
//    }
}