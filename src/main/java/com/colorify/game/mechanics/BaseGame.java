package com.colorify.game.mechanics;

import com.colorify.colorify.controller.errors.IllegalMoveException;
import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.floodfill.FloodFill;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import com.colorify.game.mechanics.scoreTracker.ColorifyScore;
import com.colorify.game.mechanics.scoreTracker.ColorifyScoreTracker;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.errors.IllegalStateError;
import com.platform.core.game.*;
import com.platform.core.utility.RandomGenerator;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class BaseGame extends AbstractBaseGame {
    private Board board;
    private ColorifyPalette palette;
    private ScoreTracker scoreTracker;
    private ArrayList<CellCoordinate> playerCells;
    private GameState state;

//    private BaseGameHistory history;

    private int maxPlayerCount;

    public BaseGame() {
        state = GameState.NOT_INITIALIZED;
        init();
    }

    @Override
    public void init() {
        gameId = RandomGenerator.getInstance().getUUID();
        gameConfiguration = new GameConfiguration();
        board = new Board(gameConfiguration);
        palette = new ColorifyPalette(board, gameConfiguration);
        maxPlayerCount = gameConfiguration.getPlayerCount();
        playerCells = new ArrayList<>();
        scoreTracker = new ColorifyScoreTracker(maxPlayerCount);
        state = GameState.WAITING_FOR_PLAYERS_TO_JOIN;
//        history = new BaseGameHistory(this);
        playerCells = new ArrayList<>();
    }

    @Override
    public String addPlayer(String playerId) throws IllegalStateError {
        if (!GameState.WAITING_FOR_PLAYERS_TO_JOIN.equals(state)) {
            throw new IllegalStateError("game not in desired state for adding player");
        }
        for (CellCoordinate coordinate : playerCells) {
            String id = coordinate.getPlayerId();
            if (id.equals(playerId))
                throw new IllegalArgumentException("Player Already Present");
        }
        CellCoordinate coordinate = new CellCoordinate();
        coordinate.setPlayerId(playerId);
        playerCells.add(coordinate);
        if (playerCells.size() == maxPlayerCount) {
            state = GameState.ALL_PLAYER_JOINED;
        }
        return state.getValue();
    }

    @Override
    public String start() throws IllegalStateError {
        if (!GameState.ALL_PLAYER_JOINED.equals(state))
            throw new IllegalStateError("game not in desired state:");
        else state = GameState.START;

        assignCoordinates();
        populateScoreTracker();
        return state.getValue();
    }

    @Override
    public void makeMove(String playerId, Cell newCell) throws IllegalMoveException {
        CellCoordinate coordinate = findPlayer(playerId);

        FloodFill floodFill = new FloodFill();

        floodFill.floodFill(board, coordinate.getR(), coordinate.getC(), board.getCell(coordinate.getR(), coordinate.getC()), newCell);

        int count = floodFill.countFill(board, coordinate.getR(), coordinate.getC(), newCell);
        updateScoreTracker(count, coordinate);
    }

    private void updateScoreTracker(int count, CellCoordinate coordinate) {

    }

    private CellCoordinate findPlayer(String playerId) throws IllegalMoveException {
        for (CellCoordinate coordinate : playerCells)
            if (coordinate.getPlayerId().equals(playerId))
                return coordinate;
        throw new IllegalMoveException("player not found" + playerId);
    }

    @Override
    public void waitForOpponent() {

    }

    @Override
    public void validate() {

    }

    @Override
    public void finish() {

    }

    @Override
    public void awards() {

    }

    @Override
    public void terminate() {

    }

    private void populateScoreTracker() {
        FloodFill floodFill = new FloodFill();
        for (CellCoordinate coordinate : playerCells) {
            int count1 = floodFill.countFill(board, coordinate.getR(), coordinate.getC(), board.getCell(coordinate.getR(), coordinate.getC()));
            Score scorePn = new ColorifyScore(count1);
            scoreTracker.addScore(coordinate.getPlayerId(), scorePn);
        }
    }

    private void assignCoordinates() {
        // 2Player Game.
        playerCells.get(0).setR(0);
        playerCells.get(0).setC(0);

        playerCells.get(0).setR(board.getRows() - 1);
        playerCells.get(0).setC(board.getCols() - 1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("============Board========\n");
        for (ArrayList<Cell> cellRow : board.getGrid()) {
            for (Cell cell : cellRow) {
                stringBuilder.append(cell.getCell()).append(" ");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("===========Palette============\n");
        for (Cell paletteCell : palette.getPaletteCells()) {
            stringBuilder.append("\t\t").append(paletteCell.getCell());
        }

        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("===========Score============\n");
        for (CellCoordinate coordinate : playerCells)
        {
            stringBuilder.append("id : ").append(coordinate.getPlayerId(), 0, 5).append("\t");
            stringBuilder.append("r").append(coordinate.getR()).append(" c").append(coordinate.getC()).append("\t");
            stringBuilder.append("score: " ).append(scoreTracker.getScores().get(coordinate.getPlayerId()));
            stringBuilder.append("\n");
        }

        stringBuilder.append("=======================================");
        stringBuilder.append("\n\n");
        return stringBuilder.toString();
    }
}
