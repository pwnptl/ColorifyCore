package com.colorify.colorify;

import com.colorify.game.GameFacade;
import com.colorify.game.PlayerFacade;
import com.platform.core.registry.messageHandler.MessageHandlerInterface;
import com.platform.core.registry.messageHandler.MessageHandlerRegistry;
import com.platform.core.registry.messageHandler.MessageHandlerType;
import com.platform.core.utility.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PostBeanInit implements InitializingBean {

    @Autowired
    PlayerFacade playerFacade;

    @Autowired
    GameFacade gameFacade;

    /**
     * Add Handlers for each messageType.
     * This function is executed Once after all Spring Bean/Components are initialised.
     * reference : <a href="https://www.baeldung.com/running-setup-logic-on-startup-in-spring">Logic on Spring Startup.</a>
     * */
    @Override
    public void afterPropertiesSet() throws Exception {
        Logger.info(this.getClass().getName(), "afterPropertiesSet called. Adding Handlers. ");
        MessageHandlerRegistry messageHandlerRegistry = MessageHandlerRegistry.getInstance();

        messageHandlerRegistry.put(MessageHandlerType.GET_PLAYER_DATA, playerFacade.getGetPlayerRequestHandler());
        messageHandlerRegistry.put(MessageHandlerType.CREATE_PLAYER, playerFacade.getCreatePlayerRequestHandler());
        messageHandlerRegistry.put(MessageHandlerType.CREATE_GAME, gameFacade.getCreateGameMessageHandler());
        messageHandlerRegistry.put(MessageHandlerType.JOIN_GAME, gameFacade.getJoinGameMessageHandler());
        messageHandlerRegistry.put(MessageHandlerType.DEFAULT, new MessageHandlerInterface() {
            @Override
            public void handleMessage(String sessionId, String message) throws IOException {
                Logger.info("Message Received DEFAULT type : " + message);
            }
        });
    }
}
