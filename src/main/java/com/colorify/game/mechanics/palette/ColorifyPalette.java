package com.colorify.game.mechanics.palette;

import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.cell.IntegerCell;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Cell;
import com.platform.core.game.Palette;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorifyPalette extends Palette {
    @Getter
    private ArrayList<Cell> paletteCells;

    private final int colorCount;

    public ColorifyPalette(final Board board, final GameConfiguration gameConfiguration) {
        colorCount = gameConfiguration.getPaletteColourCount();
        paletteCells = new ArrayList<Cell>();
        addAllCellsIntoPalette(gameConfiguration);
        removeAlreadyPresentCells(board);
    }

    private void removeAlreadyPresentCells(Board board) {
        List<Cell> presentCells =
                Arrays.asList(board.getCell(0, 0), board.getCell(board.getRows() - 1, board.getCols() - 1));
        for (Cell boardCell : presentCells) {
            paletteCells.removeIf(paletteCell -> boardCell.getCellValue() == paletteCell.getCellValue());
        }

    }

    private void addAllCellsIntoPalette(final GameConfiguration gameConfiguration) {
        // add all cells;
        for (int i = 0; i < gameConfiguration.getColourCount(); ++i) {
            paletteCells.add(new IntegerCell(i));
        }
    }
}