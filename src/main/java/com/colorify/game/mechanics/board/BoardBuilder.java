package com.colorify.game.mechanics.board;

import com.colorify.game.mechanics.cell.CellBuilder;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Cell;
import com.platform.core.utility.Logger;

import java.util.ArrayList;

public class BoardBuilder {
    private GameConfiguration gameConfiguration;
    private Board board;

    public BoardBuilder withDefaultBoard() {

        board = new Board(gameConfiguration);
        ArrayList<ArrayList<Cell>> grid = populateGrid(gameConfiguration);
        Logger.info("created Board with rows " + gameConfiguration.getRows() + ", and cols " + gameConfiguration.getColumns());
        Logger.info("created Board " + grid.toString());
        board.setGrid(grid);
        return this;
    }

    private ArrayList<ArrayList<Cell>> populateGrid(final GameConfiguration gameConfiguration) {
        int rows = gameConfiguration.getRows();
        int cols = gameConfiguration.getColumns();
        CellBuilder cellBuilder = new CellBuilder();

        ArrayList<ArrayList<Cell>> grid = new ArrayList<>(rows);
        for (int r = 0; r < rows; ++r) {
            ArrayList<Cell> row = new ArrayList<Cell>(cols);
            for (int c = 0; c < cols; ++c) {
                Cell cell = cellBuilder.getIntegerCell(gameConfiguration);
//                cell.setCoordinate(r, c);
                row.add(cell);
            }
            grid.add(row);
        }

        // players should have a different starting values.
        while (grid.get(0).get(0).getCell() == grid.get(rows - 1).get(cols - 1).getCell()) {
            grid.get(0).set(0, cellBuilder.getIntegerCell(gameConfiguration));
        }
        return grid;
    }

    public BoardBuilder withConfiguration(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
        return this;
    }

    public Board build() {
        validateBoardPresent();
        validateConfigurationPresent();

        Board board = new Board(gameConfiguration);
        board.setGrid(populateGrid(gameConfiguration));
        return board;
    }

    private void validateBoardPresent()
    {

        if (board == null)
            throw new UnsupportedOperationException("board not initialised");
    }
    private void validateConfigurationPresent()
    {
        if (gameConfiguration == null)
            throw new UnsupportedOperationException("gameConfiguration not initialised");
    }
}
