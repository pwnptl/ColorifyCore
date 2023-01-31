package com.platform.core.player;

public class PlayerBuilder {

    public Player buildHuman(final String name) {
        Player player = new HumanPlayer(name);
        return player;
    }

    public Player buildbot() throws Exception {
        throw new Exception("not implemented");
    }
}
