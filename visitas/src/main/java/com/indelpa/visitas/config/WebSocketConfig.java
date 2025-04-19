package com.indelpa.visitas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.indelpa.visitas.websocket.VisitaWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final VisitaWebSocketHandler visitaWebSocketHandler;

    public WebSocketConfig(VisitaWebSocketHandler visitaWebSocketHandler) {
        this.visitaWebSocketHandler = visitaWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(visitaWebSocketHandler, "/ws/visitas").setAllowedOrigins("*");
    }
}
