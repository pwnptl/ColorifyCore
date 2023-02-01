package com.colorify.game.mechanics.palette;

import com.colorify.game.mechanics.cell.BoardCell;
import com.colorify.game.mechanics.cell.IntegerBoardCell;
import com.colorify.game.utilities.GameConfiguration;

import java.util.ArrayList;

public class Palette {
    private ArrayList<BoardCell> palette;

    public Palette(GameConfiguration gameConfiguration) {
        int count = gameConfiguration.getColourCount() - gameConfiguration.getNumberOfPlayers();
        palette = new ArrayList<BoardCell>(count);
    }
}