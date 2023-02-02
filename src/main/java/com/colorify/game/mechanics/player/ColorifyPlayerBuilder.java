package com.colorify.game.mechanics.player;

import com.platform.core.player.Player;
import com.platform.core.player.PlayerBuilder;

public class ColorifyPlayerBuilder extends PlayerBuilder {

    @Override
    public Player buildHuman(String name)
    {
        return new ColorifyHumanPlayer(name);
    }
}
