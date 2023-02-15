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

    public String initGame() {
        AbstractBaseGame baseGame = new BaseGame();
        database.putGame(baseGame.getGameId(), baseGame);
        GameDataResponse response = new GameDataResponse((BaseGame) baseGame);
        return ObjectJsonConverter.toJSON(response);
    }

    public String addPlayer(@NonNull String gameId, String playerId) {
        try {
            BaseGame game = getGame(gameId);
            Logger.info("game from DB" + ObjectJsonConverter.toJSON(game));
            game.addPlayer(playerId);
            database.updateGame(gameId, game);
            return ObjectJsonConverter.toJSON(game);
        } catch (IllegalStateError e) {
            throw new RuntimeException(e);
        }
    }

    public String moveNumber(String gameId, String playerId, String moveNo) {
        BaseGame game = getGame(gameId);
        Logger.info("game from DB" + ObjectJsonConverter.toJSON(game));
        game.makeMove(getPlayer(playerId), new IntegerCell(Integer.parseInt(moveNo)));
        return ObjectJsonConverter.toJSON(game);
    }

    private Player getPlayer(String playerId) {
        return playerFacade.getPlayer(playerId);
    }

    private BaseGame getGame(String gameId) {
        return (BaseGame) database.queryGame(gameId, BaseGame.class);
    }
}