package com.colorify.game.mechanics.palette;

import com.colorify.game.mechanics.cell.BoardCell;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Palette;
import lombok.Getter;

import java.util.ArrayList;

public class ColorifyPalette extends Palette {
    @Getter
    private ArrayList<BoardCell> palette;

    public ColorifyPalette(GameConfiguration gameConfiguration) {
        int count = gameConfiguration.getColourCount() - gameConfiguration.getNumberOfPlayers();
        palette = new ArrayList<BoardCell>(count);
    }
}