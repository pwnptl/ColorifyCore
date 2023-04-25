package com.colorify.game;

import com.colorify.colorify.controller.errors.IllegalMoveException;
import com.colorify.colorify.model.responseBuilder.GameDataResponse;
import com.colorify.game.mechanics.BaseFacade;
import com.colorify.game.mechanics.BaseGame;
import com.colorify.game.mechanics.GameBuilder;
import com.colorify.game.mechanics.cell.IntegerCell;
import com.colorify.game.request.CreateGameRequest;
import com.colorify.game.request.GetGameRequest;
import com.colorify.game.request.JoinGameRequest;
import com.colorify.game.response.CreateGameResponse;
import com.colorify.game.response.GetGameResponse;
import com.colorify.game.response.JoinGameResponse;
import com.colorify.game.utilities.RequestResponseHelper;
import com.platform.core.database.AbstractDatabase;
import com.platform.core.errors.IllegalStateError;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.network.SessionsManager;
import com.platform.core.network.WebSocketHandlerHelper;
import com.platform.core.player.Player;
import com.platform.core.registry.messageHandler.MessageHandlerInterface;
import com.platform.core.registry.messageHandler.MessageHandlerType;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GameFacade extends BaseFacade {

    WebSocketHandlerHelper webSocketHandlerHelper = new WebSocketHandlerHelper();

    private final PlayerFacade playerFacade = new PlayerFacade(); // todo: is this right?

    private final AbstractDatabase database;

    public GameFacade() {
        database = AbstractDatabase.getInstance(getTypeAdapters());
    }

    public GameDataResponse initGame() {
        AbstractBaseGame baseGame = new GameBuilder().addDefaultBoard().build();
        database.putGame(baseGame.getGameId(), baseGame);
        GameDataResponse response = new GameDataResponse((BaseGame) baseGame, null);
        return response;
    }

    // todo : if not joined, should throw error.
    public GameDataResponse addPlayer(@NonNull String gameId, String playerId) {
        BaseGame game = null;
        try {
            game = getGame(gameId);
            String addPlayerMessage = game.addPlayer(playerId);
            saveGame(gameId, game);
            GameDataResponse response = new GameDataResponse(game, addPlayerMessage);
            return response; // broadcast is able o send the message to every user. then who are we returning this value to?
        } catch (IllegalStateError e) {
            Logger.error(e.getLocalizedMessage());
            return new GameDataResponse(game, e.getLocalizedMessage());
        }
    }

    public String makeMove(String gameId, String playerId, String moveNo) {
        BaseGame game = null;
        try {
            game = getGame(gameId);
            game.makeMove(playerId, new IntegerCell(Integer.parseInt(moveNo)));
        } catch (IllegalMoveException e) {
            throw new RuntimeException(e);
        }
        String jsonData = ObjectJsonConverter.toJSON(game);
//        myWebSocketHandler.boradcast(getConnectedSessions(game), jsonData);
        return jsonData;
    }

    private List<String> getConnectedSessions(BaseGame game) {
        return game.getPlayerIds().stream()
                .map(playerFacade::getPlayer)
                .map(Player::getSessionId)
                .collect(Collectors.toList());
    }

    public GameDataResponse startGame(final String gameId) {

        BaseGame game = getGame(gameId);
        GameDataResponse response = null;
        try {
            String startMessage = game.start();
            saveGame(gameId, game);
            response = new GameDataResponse(game, startMessage);
        } catch (IllegalStateError stateError) {
            Logger.error(stateError.getLocalizedMessage());
            response = new GameDataResponse(game, stateError.getLocalizedMessage());
        }
        return response;
    }

    private Player getPlayer(String playerId) {
        return playerFacade.getPlayer(playerId);
    }

    private BaseGame getGame(final String gameId) {
        BaseGame game = (BaseGame) database.queryGame(gameId, BaseGame.class);
//        Logger.info("game from DB" + game.toString());
        return game;
    }

    private void saveGame(final String gameId, BaseGame game) {
//        Logger.info("game to DB" + ObjectJsonConverter.toJSON(game));
        database.updateGame(gameId, game);
    }

    public String get(String gameId) {
        return getGame(gameId).toString();
    }

    @Getter
    private final MessageHandlerInterface createGameMessageHandler = new MessageHandlerInterface() {
        @Override
        public void handleMessage(String sessionId, String message) throws IOException {
            CreateGameRequest createGameRequest = (CreateGameRequest) RequestResponseHelper.fromJson(message, CreateGameRequest.class);

            GameDataResponse gameDataResponse = initGame();
            gameDataResponse = addPlayer(gameDataResponse.getGameId(), createGameRequest.getCurrentPlayerId());

            CreateGameResponse createGameResponse = new CreateGameResponse(gameDataResponse.getGameId(), gameDataResponse.getState().getValue());

            webSocketHandlerHelper.sendMessageByPlayerId(createGameRequest.getCurrentPlayerId(), MessageHandlerType.GAME_CREATED, createGameResponse);
        }
    };
    @Getter
    private final MessageHandlerInterface joinGameMessageHandler = new MessageHandlerInterface() {
        @Override
        public void handleMessage(String sessionId, String message) throws IOException {
            JoinGameRequest joinGameRequest = (JoinGameRequest) RequestResponseHelper.fromJson(message, JoinGameRequest.class);
            JoinGameResponse joinGameResponse = null;
            GameDataResponse gameDataResponse = addPlayer(joinGameRequest.getGameId(), joinGameRequest.getPlayerId());
            try {
                joinGameResponse = new JoinGameResponse(gameDataResponse.getGameId(), true, gameDataResponse.getPlayerList(), gameDataResponse.getState());
//                String payload = new Payload(MessageHandlerType.GAME_JOINED.getValue(), joinGameResponse).asJson();
//                Logger.info("Payload for join" , payload);
                webSocketHandlerHelper.broadcastMessageByPlayerIds(gameDataResponse.getPlayers().keySet(), MessageHandlerType.GAME_JOINED, joinGameResponse);
            } catch (Exception e) {
                // todo: catch exception specific to joining game and provide Reason/ReasonCode to Client.
                Logger.info("JOIN GAME HANDLER", e.getMessage());
                joinGameResponse = new JoinGameResponse(joinGameRequest.getGameId(), false, gameDataResponse.getPlayerList(), gameDataResponse.getState());
                webSocketHandlerHelper.sendMessageByPlayerId(joinGameRequest.getPlayerId(), MessageHandlerType.GAME_JOINED, joinGameResponse);
            }
        }
    };

    @Getter
    private final MessageHandlerInterface getGameMessageHandler = new MessageHandlerInterface() {
        @Override
        public void handleMessage(String sessionId, String message) throws IOException {
            GetGameRequest getGameRequest = (GetGameRequest) RequestResponseHelper.fromJson(message, GetGameRequest.class);
            GetGameResponse getGameResponse = null;
            try {
                String gameId = getGameRequest.getGameId();
                GameDataResponse gameDataResponse = new GameDataResponse(getGame(gameId), null);
                getGameResponse = new GetGameResponse(SessionsManager.getInstance().findPlayerIdBySessionId(sessionId), gameDataResponse); // can we get playerId from somewhere else ?
                webSocketHandlerHelper.sendMessageBySessionId(sessionId, MessageHandlerType.GAME_DATA, getGameResponse);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    };
}