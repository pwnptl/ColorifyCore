package com.platform.core.player;

public class PlayerBuilder {

    public Player buildHuman(final String name) {
        return new HumanPlayer(name);
    }

    public Player buildBot() throws Exception {
        throw new Exception("not implemented");
    }
}
