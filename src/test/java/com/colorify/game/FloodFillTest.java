package com.colorify.game;

import com.colorify.game.mechanics.floodfill.FloodFill;
import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.cell.IntegerCell;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Cell;
import com.platform.core.utility.RandomGenerator;
import org.junit.jupiter.api.Test;

class FloodFillTest {
    // manual check required dur to randomiser.
    // todo: add concrete UT.
    @Test
    public void testFloodFill4x4() {
        performFoolding(4, 4, 3);
    }



    @Test
    public void testFloodFill6x6() {
        performFoolding(6, 6, 8);
    }

    private Board performFoolding(int r, int c, int iterations) {
        GameConfiguration gameConfiguration = new GameConfiguration(r, c, 2, 4);
        Board board = new Board(gameConfiguration);

        printBoard(board);

        FloodFill floodFill = new FloodFill();
        for (int i = 0; i < iterations; ++i) {
            Cell prevCell = new IntegerCell(board.getCell(0, 0).getCellValue());
            Cell newCell;
            do {
                newCell = new IntegerCell(RandomGenerator.getInstance().getRandNumber(board.getUniqueColors()));
            } while (newCell.getCellValue() == prevCell.getCellValue());

            assert prevCell.getCellValue() != newCell.getCellValue();
            System.out.println("OldCell " + prevCell.getCellValue());
            System.out.println("newCell " + newCell.getCellValue());
            floodFill.floodFill(board, 0, 0, prevCell, newCell);
            int newCount = floodFill.countFill(board, 0, 0, newCell);
            printBoard(board);
            System.out.println(newCount);
            System.out.println();
        }
        return board;
    }

    private void printBoard(Board board) {
        for (int i = 0; i < board.getRows(); ++i) {
            for (int j = 0; j < board.getCols(); ++j) {
                System.out.print(board.getCell(i, j).getCellValue() + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

}