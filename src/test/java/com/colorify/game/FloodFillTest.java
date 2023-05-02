package com.colorify.game;

import com.colorify.game.mechanics.Strategies.FloodFill;
import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.board.BoardBuilder;
import com.colorify.game.mechanics.cell.IntegerCell;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Cell;
import com.platform.core.utility.RandomGenerator;
import org.junit.jupiter.api.Test;

class FloodFillTest {
    // manual check required due to randomiser.
    // todo: add concrete UT.
    @Test
    public void testFloodFill4x4() {
        performFlooding(4, 4, 3);
    }


    @Test
    public void testFloodFill6x6() {
        performFlooding(6, 6, 8);
    }

    private Board performFlooding(int r, int c, int iterations) {
        GameConfiguration gameConfiguration = new GameConfiguration(r, c, 2, 4);
        Board board = new BoardBuilder().withConfiguration(gameConfiguration).withDefaultBoard().build();

        printBoard(board);

        FloodFill floodFill = new FloodFill();
        for (int i = 0; i < iterations; ++i) {
            Cell prevCell = new IntegerCell(board.getCell(0, 0).getCell());
            Cell newCell;
            do {
                newCell = new IntegerCell(RandomGenerator.getInstance().getRandNumber(board.getUniqueColors()));
            } while (newCell.getCell() == prevCell.getCell());

            assert prevCell.getCell() != newCell.getCell();
            System.out.println("OldCell " + prevCell.getCell());
            System.out.println("newCell " + newCell.getCell());
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
                System.out.print(board.getCell(i, j).getCell() + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

}