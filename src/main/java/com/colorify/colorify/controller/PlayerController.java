package com.colorify.colorify.controller;

import com.colorify.game.PlayerFacade;
import com.colorify.game.utilities.RequestResponseHelper;
import com.platform.core.network.SessionsManager;
import com.platform.core.player.Player;
import com.platform.core.registry.messageHandler.MessageHandlerInterface;
import com.platform.core.registry.messageHandler.MessageHandlerType;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

@RestController
@RequestMapping("/player/")
public class PlayerController extends BaseController {

    @Autowired
    private PlayerFacade playerFacade;

    @Autowired
    private SessionsManager sessionsManager;

    @GetMapping("/create")
    public String createPlayer(@RequestParam(value = "name", defaultValue = "dummy") String name) {
        Logger.info("\ncreatePlayer api called");
        return ObjectJsonConverter.toJSON(playerFacade.createPlayer(name));
    }

    @GetMapping("/get")
    public String getPlayer(@RequestParam(value = "playerId", defaultValue = "dummyId") String id) {
        Logger.info("\ncreatePlayer api called");
        return ObjectJsonConverter.toJSONWithType(MessageHandlerType.PLAYER_DATA.name(), playerFacade.getPlayer(id));
    }

    @Getter
    private final MessageHandlerInterface getPlayerRequestHandler = new MessageHandlerInterface() {
        @Override
        public void handleMessage(String sessionId, String message) throws IOException {
            String messageId = RequestResponseHelper.getPlayerIdFromMessage(message);
            Player player = playerFacade.getPlayer(messageId);

            String response = ObjectJsonConverter.toJSONWithType(MessageHandlerType.PLAYER_DATA.name(), player);
            sessionsManager.get(sessionId).sendMessage(new TextMessage(response));
        }
    };
}