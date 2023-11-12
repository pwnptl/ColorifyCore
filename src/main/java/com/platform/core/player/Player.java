package com.platform.core.player;

import com.platform.core.utility.RandomGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public abstract class Player implements Serializable {

    protected String playerId;
    protected String name;
    protected PlayerType type;

//    @Setter
//    protected String SessionId;

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
