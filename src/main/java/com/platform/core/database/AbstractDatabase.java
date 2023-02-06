package com.platform.core.database;

import com.colorify.game.mechanics.BaseGame;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.player.Player;

public abstract class AbstractDatabase {

    protected static AbstractDatabase instance;

    protected AbstractDatabase() {
        init();
    }

    public static AbstractDatabase getInstance() {
        if (instance == null)
            instance = new MongoDB(); // todo: initiate via Config.
        return instance;
    }


    public abstract void init();

    // scalability issue ??? -> can we segregate MODELS from DB_OPERATIONS ??
    public abstract boolean putPlayer(final String id, final Player data);

    public abstract boolean putGame(final String id, final AbstractBaseGame data);

    public abstract Player queryPlayer(final String id);

    public abstract AbstractBaseGame queryGame(final String id, Class<BaseGame> baseGameClass);

    public abstract boolean updatePlayer(String gameId, BaseGame game);

    public abstract boolean updateGame(String gameId, BaseGame game);

    public abstract boolean deletePlayer(final String id);

    public abstract boolean deleteGame(final String id);
}
