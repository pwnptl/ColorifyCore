package com.colorify.game.mechanics.board;

import com.platform.core.game.BoardCell;
import com.colorify.game.mechanics.cell.IntegerBoardCell;
import com.colorify.game.utilities.Constants;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.utility.Logger;
import lombok.Getter;

import java.util.ArrayList;

public class Board {

    @Getter
    private ArrayList<ArrayList<BoardCell>> grid;
    private int rows;
    private int cols;

    private int uniqueColors;


    public Board(GameConfiguration gameConfiguration) {
        rows = Constants.DEFAULT_BOARD_ROWS;
        cols = Constants.DEFAULT_BOARD_COLS;
        uniqueColors = Constants.DEFAULT_BOARD_COLORS;
        createBoard(gameConfiguration);
    }

    private void createBoard(GameConfiguration gameConfiguration) {
        grid = new ArrayList<>(rows);
        for (int r = 0; r < rows; ++r) {
            ArrayList<BoardCell> row = new ArrayList<BoardCell>(cols);
            for (int c = 0; c < cols; ++c) {
                row.add(new IntegerBoardCell(gameConfiguration));
            }
            grid.add(row);
        }
        Logger.info("created Board with rows " + rows + ", and cols " + cols);
        Logger.info("created Board " + grid.toString());
    }

    public BoardCell getCell(final int r, final int c) {
        return grid.get(r).get(c);
    }
}
