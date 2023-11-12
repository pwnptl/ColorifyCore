package com.platform.core.player;

import java.io.Serializable;

public class HumanPlayer extends Player implements Serializable{
    public HumanPlayer(String id, String name) {
        super(PlayerType.HUMAN, id, name);
    }

    public HumanPlayer(String name) {
        super(PlayerType.HUMAN, name);
    }
}
