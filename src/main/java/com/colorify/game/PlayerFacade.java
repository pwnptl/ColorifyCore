package com.colorify.game;

import com.colorify.game.mechanics.BaseFacade;
import com.colorify.game.request.GetPlayer;
import com.colorify.game.utilities.RequestResponseHelper;
import com.platform.core.database.AbstractDatabase;
import com.platform.core.network.Payload;
import com.platform.core.network.SessionsManager;
import com.platform.core.player.HumanPlayer;
import com.platform.core.player.Player;
import com.platform.core.registry.messageHandler.MessageHandlerInterface;
import com.platform.core.registry.messageHandler.MessageHandlerType;
import com.platform.core.utility.Logger;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

@Component
public class PlayerFacade extends BaseFacade {

    private final AbstractDatabase database;

    public PlayerFacade() {
        database = AbstractDatabase.getInstance(getTypeAdapters());
    }

    public String createPlayer(@NonNull String name) {
        Player player = new HumanPlayer(name);
        database.putPlayer(player.getPlayerId(), player);
        return player.getPlayerId();
    }

    public Player getPlayer(@NonNull String playerId) {
        return database.queryPlayer(playerId);
    }


    @Getter
    private final MessageHandlerInterface getPlayerRequestHandler = new MessageHandlerInterface() {
        @Override
        public void handleMessage(String sessionId, String message) throws IOException {
            Logger.info("message = " + message);
            GetPlayer getPlayer = RequestResponseHelper.getPlayerIdRequest(message);
            Player player = getPlayer(getPlayer.getPlayerId());

            String payload = new Payload(MessageHandlerType.PLAYER_DATA.name(), player).asJson();

            Logger.info("response = " + payload);
            SessionsManager.getInstance().get(sessionId).sendMessage(new TextMessage(payload));
        }
    };
}
