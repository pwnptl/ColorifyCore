package com.colorify.game.mechanics.board;

import com.colorify.game.mechanics.cell.IntegerCell;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Cell;
import com.platform.core.utility.Logger;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Board {

    private ArrayList<ArrayList<Cell>> grid;
    private final int rows;
    private final int cols;
    private final int uniqueColors;


    public Board(final GameConfiguration gameConfiguration) {
        rows = gameConfiguration.getRows();
        cols = gameConfiguration.getColumns();
        uniqueColors = gameConfiguration.getColourCount();
        createBoard(gameConfiguration);
    }

    private void createBoard(final GameConfiguration gameConfiguration) {
        grid = new ArrayList<>(rows);
        for (int r = 0; r < rows; ++r) {
            ArrayList<Cell> row = new ArrayList<Cell>(cols);
            for (int c = 0; c < cols; ++c) {
                Cell cell = new IntegerCell(gameConfiguration);
                cell.setCoordinate(r, c);
                row.add(cell);
            }
            grid.add(row);
        }
        while (grid.get(0).get(0).getCellValue() == grid.get(rows - 1).get(cols - 1).getCellValue()) {
            grid.get(0).set(0, new IntegerCell(gameConfiguration));
        }
        Logger.info("created Board with rows " + rows + ", and cols " + cols);
        Logger.info("created Board " + grid.toString());
    }

    public Cell getCell(final int r, final int c) {
        return grid.get(r).get(c);
    }
}
