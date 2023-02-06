package com.colorify.game;

import com.colorify.game.mechanics.BaseGame;
import com.platform.core.database.AbstractDatabase;
import com.platform.core.game.AbstractBaseGame;

public class GameFacade {

    private final AbstractDatabase database;

    public GameFacade()
    {
        database = AbstractDatabase.getInstance();
    }
    public String initGame() {
        AbstractBaseGame baseGame = new BaseGame();

        database.putGame(baseGame.getId(), baseGame);
        return baseGame.toString();
    }
}
