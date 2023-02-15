package com.colorify.game;

import com.colorify.colorify.model.responseBuilder.GameDataResponse;
import com.colorify.game.mechanics.BaseFacade;
import com.colorify.game.mechanics.BaseGame;
import com.colorify.game.mechanics.cell.IntegerCell;
import com.platform.core.database.AbstractDatabase;
import com.platform.core.errors.IllegalStateError;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.player.Player;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import lombok.NonNull;

public class GameFacade extends BaseFacade {

    private final PlayerFacade playerFacade = new PlayerFacade(); // todo: is this right?

    private final AbstractDatabase database;

    public GameFacade() {
        database = AbstractDatabase.getInstance(getTypeAdapters());
    }

    public GameDataResponse initGame() {
        AbstractBaseGame baseGame = new BaseGame();
        database.putGame(baseGame.getGameId(), baseGame);
        GameDataResponse response = new GameDataResponse((BaseGame) baseGame, null);
        return response;
    }

    public GameDataResponse addPlayer(@NonNull String gameId, String playerId) {
        BaseGame game = null;
        try {
            game = getGame(gameId);
            String addPlayerMessage = game.addPlayer(playerId);
            saveGame(gameId, game);
            return new GameDataResponse(game, addPlayerMessage);
        } catch (IllegalStateError e) {
            Logger.error(e.getLocalizedMessage());
            return new GameDataResponse(game, e.getLocalizedMessage());
        }
    }

    public String moveNumber(String gameId, String playerId, String moveNo) {
        BaseGame game = getGame(gameId);
        Logger.info("game from DB" + ObjectJsonConverter.toJSON(game));
        game.makeMove(getPlayer(playerId), new IntegerCell(Integer.parseInt(moveNo)));
        return ObjectJsonConverter.toJSON(game);
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
        Logger.info("game from DB" + ObjectJsonConverter.toJSON(game));
        return game;
    }

    private void saveGame(final String gameId, BaseGame game) {
        Logger.info("game to DB" + ObjectJsonConverter.toJSON(game));
        database.updateGame(gameId, game);
    }
}