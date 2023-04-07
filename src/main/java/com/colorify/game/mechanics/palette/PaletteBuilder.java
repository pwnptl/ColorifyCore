package com.colorify.game.mechanics.palette;

import com.colorify.game.mechanics.board.Board;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Palette;

public class PaletteBuilder {

    private GameConfiguration gameConfiguration;

    public Palette build(Board board) {
        return new ColorifyPalette(board, gameConfiguration);
    }


    public PaletteBuilder withConfiguration(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
        return this;
    }
}
