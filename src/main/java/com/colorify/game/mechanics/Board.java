package com.colorify.game.mechanics;

import com.colorify.game.mechanics.cell.BoardCell;
import com.colorify.game.mechanics.cell.IntegerBoardCell;
import com.colorify.game.utilities.Constants;

public class Board {

    private BoardCell[][] grid;
    private int rows;
    private int cols;

    private int uniqueColors;


    Board() {
        rows = Constants.DEFAULT_BOARD_ROWS;
        cols = Constants.DEFAULT_BOARD_COLS;
        uniqueColors = Constants.DEFAULT_BOARD_COLORS;
        createBoard();
    }

    public void createBoard() {
        grid = new IntegerBoardCell[rows][cols];
    }

    public BoardCell getCell(final int r, final int c) {
        return grid[r][c];
    }
}