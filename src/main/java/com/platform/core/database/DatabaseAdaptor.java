package com.platform.core.database;

import com.platform.core.game.AbstractBaseGame;
import com.platform.core.player.Player;

public class DatabaseAdaptor {
    private final AbstractDatabase abstractDatabase;

    public DatabaseAdaptor() {
        abstractDatabase = AbstractDatabase.getInstance();
    }

    public boolean putPlayer(final Player player) {
        return abstractDatabase.put(player.getId(), player);
    }

    public boolean putGame(final AbstractBaseGame abstractBaseGame) {
        return abstractDatabase.put(abstractBaseGame.getId(), abstractBaseGame);
    }
}
