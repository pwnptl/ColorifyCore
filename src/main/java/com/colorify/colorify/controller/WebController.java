package com.colorify.colorify.controller;

import com.platform.core.network.MyWebSocketHandler;
import com.platform.core.utility.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebController implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/myHandler");
        Logger.info("Adding myHandler");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new MyWebSocketHandler();
    }
}
