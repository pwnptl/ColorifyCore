package com.platform.core.database;

import com.colorify.game.mechanics.BaseGame;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.player.Player;

import java.util.Map;

public abstract class AbstractDatabase {

    protected Gson gson;
    protected static AbstractDatabase instance;

    protected AbstractDatabase() {
    }

    protected AbstractDatabase(final Map<Class, Object> typeAdapters) {
        initializeGsonWithTypeAdapters(typeAdapters);
    }

    public static AbstractDatabase getInstance(final Map<Class, Object> typeAdapters) {
        if (instance == null)
            instance = new InMemoryStorage(); // todo: initiate via Config.
        return instance;
    }
    protected void initializeGsonWithTypeAdapters(final Map<Class, Object> typeAdapters) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        for (Map.Entry<Class, Object> pair : typeAdapters.entrySet()) {
            gsonBuilder.registerTypeHierarchyAdapter(pair.getKey(), pair.getValue());
        }
        gson = gsonBuilder.create();
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
