package com.colorify.game.mechanics.board;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Cell;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Board {

    private ArrayList<ArrayList<Cell>> grid;
    private final int rows;
    private final int cols;
    private final int uniqueColors;

    public Board(GameConfiguration gameConfiguration) {
        rows = gameConfiguration.getRows();
        cols = gameConfiguration.getColumns();
        uniqueColors = gameConfiguration.getColourCount();
    }

    public Cell getCell(final int r, final int c) {
        return grid.get(r).get(c);
    }
}
