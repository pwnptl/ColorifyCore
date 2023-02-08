package com.colorify.game.mechanics.player;

import com.platform.core.game.Cell;
import com.platform.core.player.HumanPlayer;

public class ColorifyHumanPlayer extends HumanPlayer {
    private Cell color;
    public ColorifyHumanPlayer(String id, String name) {
        super(id, name);
    }

    public ColorifyHumanPlayer(String name) {
        super(name);
    }
}
