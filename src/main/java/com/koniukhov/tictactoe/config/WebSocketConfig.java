package com.koniukhov.tictactoe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final String PLAYER_DESTINATION = "/player";
    private static final String APP_DESTINATION = "/app";
    private static final String STOMP_ENDPOINT = "/websocket";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker( PLAYER_DESTINATION);
        config.setApplicationDestinationPrefixes(APP_DESTINATION);
        config.setUserDestinationPrefix(PLAYER_DESTINATION);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(STOMP_ENDPOINT);
    }
}
