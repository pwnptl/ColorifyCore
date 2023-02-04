package com.platform.core.database;

import com.colorify.game.mechanics.BaseGame;
import com.platform.core.player.Player;

abstract class AbstractDatabase {

    protected static AbstractDatabase instance;

    protected AbstractDatabase() {}

    public static AbstractDatabase getInstance()
    {
        if(instance == null)
            instance = new MongoDB(); // todo: initiate via Config.
        return instance;
    }


    public abstract void init();

    // scalability issue ??? -> can we segregate MODELS from DB_OPERATIONS ??
    public abstract boolean putPlayer(final String id, final Player data);
    public abstract boolean putGame(final String id, final BaseGame data);

    public abstract String queryPlayer(final String id);
    public abstract BaseGame queryGame(final String id);

    public abstract boolean deletePlayer(final String id);
    public abstract boolean deleteGame(final String id);
}
