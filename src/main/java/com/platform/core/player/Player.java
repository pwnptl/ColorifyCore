package com.platform.core.player;

import com.colorify.game.utilities.RandomGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public abstract class Player {
    protected String id;
    protected String name;

    public Player(final String name) {
        this.name = name;
        this.id = RandomGenerator.getUUID();
    }
}
