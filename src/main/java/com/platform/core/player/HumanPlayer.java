package com.platform.core.player;

public class HumanPlayer extends Player {

    public HumanPlayer(String id, String name) {
        super(PlayerType.HUMAN, id, name);
    }

    public HumanPlayer(String name) {
        super(PlayerType.HUMAN, name);
    }
}
