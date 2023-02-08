package com.platform.core.player;

import com.google.gson.Gson;
import com.platform.core.utility.RandomGenerator;
import lombok.Getter;

@Getter
public abstract class Player {
    protected String id;
    protected String name;
    protected PlayerType type;

    public Player(final PlayerType type, final String name) {
        this.name = name;
        this.id = RandomGenerator.getInstance().getUUID();
        this.type = type;
    }

    public Player(final PlayerType type, final String id, final String name) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public static Player getPlayer(String toJson, Gson gson) {
        return null;
    }

    public static Player getPlayer(String playerId) {
        return null;
    }
}
