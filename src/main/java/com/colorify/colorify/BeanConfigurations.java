package com.colorify.colorify;

import com.colorify.game.GameFacade;
import com.colorify.game.PlayerFacade;
import com.platform.core.network.SessionsManager;
import com.platform.core.registry.messageHandler.MessageHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurations {
    @Bean
    public GameFacade gameFacade() {
        return new GameFacade();
    }

    @Bean
    public PlayerFacade playerFacade() {
        return new PlayerFacade();
    }

    @Bean
    public SessionsManager sessionsManager() {
        return SessionsManager.getInstance();
    }

    @Bean
    public MessageHandlerRegistry messageHandlerRegistry() {
        return MessageHandlerRegistry.getInstance();
    }
}
