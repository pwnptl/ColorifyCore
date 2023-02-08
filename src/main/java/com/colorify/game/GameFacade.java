package com.colorify.game;

import com.colorify.colorify.model.responseBuilder.GameDataResponse;
import com.colorify.game.mechanics.BaseFacade;
import com.colorify.game.mechanics.BaseGame;
import com.platform.core.database.AbstractDatabase;
import com.platform.core.errors.IllegalStateError;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import lombok.NonNull;

public class GameFacade extends BaseFacade {

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
            BaseGame game = (BaseGame) database.queryGame(gameId,  BaseGame.class);
            Logger.info("game from DB" + ObjectJsonConverter.toJSON(game));
            game.addPlayer(playerId);
            database.updateGame(gameId, game);
            return  ObjectJsonConverter.toJSON(game);
        } catch (IllegalStateError e) {
            throw new RuntimeException(e);
        }
    }
}