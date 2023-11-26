package com.colorify.game;

import com.colorify.game.mechanics.BaseFacade;
import com.colorify.game.request.CreatePlayerRequest;
import com.colorify.game.request.GetPlayerRequest;
import com.colorify.game.request.RegisterPlayerSessionRequest;
import com.colorify.game.response.CreatePlayerResponse;
import com.colorify.game.response.GetPlayerResponse;
import com.colorify.game.response.RegisterPlayerSessionResponse;
import com.colorify.game.utilities.RequestResponseHelper;
import com.platform.core.database.DatabaseAdapter;
import com.platform.core.network.NearbyPeerDiscoveryHelper;
import com.platform.core.network.SessionsManager;
import com.platform.core.network.WebSocketHandlerHelper;
import com.platform.core.player.HumanPlayer;
import com.platform.core.player.Player;
import com.platform.core.registry.messageHandler.MessageHandlerInterface;
import com.platform.core.registry.messageHandler.MessageHandlerType;
import com.platform.core.utility.Logger;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerFacade extends BaseFacade {

    private final DatabaseAdapter database; // todo: create using bean.
    private final WebSocketHandlerHelper webSocketHandlerHelper; // todo: create using bean.

    public PlayerFacade() {
        database = new DatabaseAdapter();
        webSocketHandlerHelper = new WebSocketHandlerHelper();
    }

    public String createPlayer(@NonNull String name) {
        Player player = new HumanPlayer(name);
        database.putPlayer(player.getPlayerId(), player);
        return player.getPlayerId();
    }

    public Player getPlayer(@NonNull String playerId) {
        try {
            return (Player) database.queryPlayer(playerId);
        } catch (NullPointerException npe) {
            return null;
        }
    }


    @Getter
    private final MessageHandlerInterface getPlayerRequestHandler = new MessageHandlerInterface() {
        @Override
        public void handleMessage(String sessionId, String message) throws IOException {
            GetPlayerRequest getPlayer = RequestResponseHelper.getPlayerIdRequest(message);
            try {
                Player player = getPlayer(getPlayer.getPlayerId());
                GetPlayerResponse getPlayerResponse = new GetPlayerResponse(player);
                Logger.info(PlayerFacade.class.getName(), "Player found : " + player.getPlayerId() + " " + player.getName());
                webSocketHandlerHelper.sendMessageByPlayerId(getPlayer.getPlayerId(), MessageHandlerType.PLAYER_DATA, getPlayerResponse);
            } catch (NullPointerException npe) {
                // invalid user Input.
                // todo : return invalid input response.
                Logger.info(PlayerFacade.class.getName(), "Player not found : " + getPlayer.getPlayerId());
            }
        }
    };

    @Getter
    private final MessageHandlerInterface createPlayerRequestHandler = new MessageHandlerInterface() {
        @Override
        public void handleMessage(String sessionId, String message) {
            CreatePlayerRequest createPlayerRequest = RequestResponseHelper.createPlayerIdRequest(message);

            String playerId = createPlayer(createPlayerRequest.getName());
            CreatePlayerResponse createPlayerResponse = new CreatePlayerResponse(playerId);

            webSocketHandlerHelper.sendMessageBySessionId(sessionId, MessageHandlerType.PLAYER_CREATED, createPlayerResponse);
        }
    };


    @Getter
    private final MessageHandlerInterface registerPlayerSessionHandler = new MessageHandlerInterface() {
        @Override
        public void handleMessage(String sessionId, String message) {
            // todo : can we refrain from using sessoionId? as it is not used in most handlers and sessionManager is used for sessionDetection.
            RegisterPlayerSessionRequest registerGameSessionRequest = (RegisterPlayerSessionRequest) RequestResponseHelper.fromJson(message, RegisterPlayerSessionRequest.class);
            SessionsManager.getInstance().addPlayerSession(registerGameSessionRequest.getUserId(), sessionId);

            RegisterPlayerSessionResponse registerGameSessionResponse = new RegisterPlayerSessionResponse(true, sessionId);
            webSocketHandlerHelper.sendMessageByPlayerId(registerGameSessionRequest.getUserId(), MessageHandlerType.PLAYER_SESSION_REGISTERED, registerGameSessionResponse);
        }
    };

    /**
     *  Blocked Peer Discovery Code.
     *  https://github.com/pwnptl/ColorifyCore/issues/7#issue-2011085970
     */
    public List<String> findNearbyPlayers() {
        return null;
        //return new NearbyPeerDiscoveryHelper().discoverPeers().stream().map(InetAddress::toString).collect(Collectors.toList());
    }
}
