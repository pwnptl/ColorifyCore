package com.colorify.colorify;

import com.colorify.game.GameFacade;
import com.colorify.game.PlayerFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurations {
    @Bean
    public GameFacade gameFacade()
    {
        return new GameFacade();
    }
    @Bean
    public PlayerFacade playerFacade()
    {
        return new PlayerFacade();
    }
}
