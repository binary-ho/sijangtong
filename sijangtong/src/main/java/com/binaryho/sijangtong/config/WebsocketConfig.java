package com.binaryho.sijangtong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 서버에서 클라이언트로 부터 메세지를 전달받을 api의 prefixes를 설정한다.
        // pub의 역할
        registry.setApplicationDestinationPrefixes("/app");

        // enable Simple Broker -
        // 메모리 기반 메세지 브로커가 해당 api를 구독하고 있는 클라이언트에게
        // 메세지를 전달한다.
        // sub의 역할 - destination의 prefix가 chatroom이 될 것이다.
        registry.enableSimpleBroker("/chatroom", "/user");

        registry.setUserDestinationPrefix("/user");
    }


    // 클라이언트에서 WebSocket을 연결할 api를 설정한다.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // path for all out website connection
        // so, here i'm just giving it as 'ws'
        // 웹소켓 서버 엔드포인트를 설정한다.
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}
