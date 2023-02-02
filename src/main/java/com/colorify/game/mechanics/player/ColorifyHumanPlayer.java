package com.colorify.game.mechanics.player;

import com.colorify.game.mechanics.cell.BoardCell;
import com.platform.core.player.HumanPlayer;

public class ColorifyHumanPlayer extends HumanPlayer {
    private BoardCell color;
    public ColorifyHumanPlayer(String id, String name) {
        super(id, name);
    }

    public ColorifyHumanPlayer(String name) {
        super(name);
    }
}
