package com.colorify.game.mechanics.floodfill;

import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.cell.IntegerCell;
import com.platform.core.game.Cell;

public class FloodFill {


    public void floodFill(final Board board, int r, int c, final Cell prevCell, final Cell newCell) {

        Cell _prevCell = new IntegerCell(prevCell.getCell());
        Cell _newCell = new IntegerCell(newCell.getCell());
        _floodFill(board, r, c, _prevCell, _newCell);
    }

    private void _floodFill(final Board board, int r, int c, final Cell prevCell, final Cell newCell) {
        if (r < 0 || board.getRows() <= r) return;
        if (c < 0 || board.getCols() <= c) return;

        if (board.getCell(r, c).getCell() == prevCell.getCell()) {
            board.getCell(r, c).setCell(newCell.getCell());
            floodFill(board, r + 1, c, prevCell, newCell);
            floodFill(board, r - 1, c, prevCell, newCell);
            floodFill(board, r, c + 1, prevCell, newCell);
            floodFill(board, r, c - 1, prevCell, newCell);
        }
    }

    public int countFill(Board board, int r, int c, Cell newCell) {
        boolean[][] isCounted = new boolean[board.getRows()][board.getCols()];
        for (int i = 0; i < board.getRows(); ++i)
            for (int j = 0; j < board.getCols(); ++j)
                isCounted[i][j] = false;
        return countFill(board, r, c, newCell, isCounted);
    }

    private int countFill(Board board, int r, int c, Cell newCell, boolean[][] isCounted) {

        if (r < 0 || board.getRows() <= r) return 0;
        if (c < 0 || board.getCols() <= c) return 0;
        int sum = 0;
        if (!isCounted[r][c] && board.getCell(r, c).getCell() == newCell.getCell()) {
            isCounted[r][c] = true;
            sum += 1;
            sum += countFill(board, r + 1, c, newCell, isCounted);
            sum += countFill(board, r - 1, c, newCell, isCounted);
            sum += countFill(board, r, c + 1, newCell, isCounted);
            sum += countFill(board, r, c - 1, newCell, isCounted);
        }
        return sum;
    }
}
