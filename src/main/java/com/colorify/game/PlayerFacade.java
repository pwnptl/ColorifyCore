package com.colorify.game;

import com.colorify.game.mechanics.BaseFacade;
import com.colorify.game.request.CreatePlayerRequest;
import com.colorify.game.request.GetPlayerRequest;
import com.colorify.game.response.CreatePlayerResponse;
import com.colorify.game.response.GetPlayerResponse;
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
            GetPlayerRequest getPlayer = RequestResponseHelper.getPlayerIdRequest(message);
            Player player = getPlayer(getPlayer.getPlayerId());

            GetPlayerResponse getPlayerResponse = new GetPlayerResponse(player);
            String payload = new Payload(MessageHandlerType.PLAYER_DATA.name(), getPlayerResponse).asJson();

            Logger.info("response = " + payload);
            SessionsManager.getInstance().get(sessionId).sendMessage(new TextMessage(payload));
        }
    };

    @Getter
    private final MessageHandlerInterface createPlayerRequestHandler = new MessageHandlerInterface() {
        @Override
        public void handleMessage(String sessionId, String message) throws IOException {
            Logger.info("message = " + message);
            CreatePlayerRequest createPlayerRequest = RequestResponseHelper.createPlayerIdRequest(message);

            String playerId = createPlayer(createPlayerRequest.getName());
            CreatePlayerResponse createPlayerResponse = new CreatePlayerResponse(playerId);

            String payload = new Payload(MessageHandlerType.PLAYER_CREATED.name(), createPlayerResponse).asJson();
            Logger.info("response = " + payload);
            SessionsManager.getInstance().get(sessionId).sendMessage(new TextMessage(payload));
        }
    };
}
