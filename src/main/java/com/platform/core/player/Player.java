package com.platform.core.player;

import com.platform.core.utility.RandomGenerator;
import lombok.Getter;

@Getter
public abstract class Player {
    protected String playerId;
    protected String name;
    protected PlayerType type;

    public Player(final PlayerType type, final String name) {
        this.name = name;
        this.playerId = RandomGenerator.getInstance().getUUID();
        this.type = type;
    }

    public Player(final PlayerType type, final String id, final String name) {
        this.name = name;
        this.playerId = id;
        this.type = type;
    }
}
