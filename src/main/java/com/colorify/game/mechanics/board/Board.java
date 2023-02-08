package com.colorify.game.mechanics.board;

import com.platform.core.game.Cell;
import com.colorify.game.mechanics.cell.IntegerCell;
import com.colorify.game.utilities.Constants;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.utility.Logger;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Board {

    private ArrayList<ArrayList<Cell>> grid;
    private final int rows;
    private final int cols;
    private final int uniqueColors;


    public Board(GameConfiguration gameConfiguration) {
        rows = Constants.DEFAULT_BOARD_ROWS;
        cols = Constants.DEFAULT_BOARD_COLS;
        uniqueColors = Constants.DEFAULT_BOARD_COLORS;
        createBoard(gameConfiguration);
    }

    private void createBoard(GameConfiguration gameConfiguration) {
        grid = new ArrayList<>(rows);
        for (int r = 0; r < rows; ++r) {
            ArrayList<Cell> row = new ArrayList<Cell>(cols);
            for (int c = 0; c < cols; ++c) {
                row.add(new IntegerCell(gameConfiguration));
            }
            grid.add(row);
        }
        while (grid.get(0).get(0).getCell() == grid.get(rows - 1).get(cols - 1).getCell()) {
            grid.get(0).set(0, new IntegerCell(gameConfiguration));
        }
        Logger.info("created Board with rows " + rows + ", and cols " + cols);
        Logger.info("created Board " + grid.toString());
    }

    public Cell getCell(final int r, final int c) {
        return grid.get(r).get(c);
    }
}
