package com.colorify.game.mechanics;

import com.colorify.colorify.controller.errors.IllegalMoveException;
import com.colorify.game.mechanics.Strategies.FloodFill;
import com.colorify.game.mechanics.Strategies.RotatingList;
import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import com.colorify.game.mechanics.scoreTracker.ColorifyScoreTracker;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.errors.IllegalStateError;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.game.Cell;
import com.platform.core.game.GameState;
import com.platform.core.game.ScoreTracker;
import com.platform.core.utility.RandomGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class BaseGame extends AbstractBaseGame {

    @Setter
    private Board board;
    @Setter
    private ColorifyPalette palette;
    private ScoreTracker scoreTracker;
    private RotatingList<CellCoordinate> playerCells;
    private int movesSoFar;
    private GameState state;

    private int maxPlayerCount;

    public BaseGame() {
        state = GameState.NOT_INITIALIZED;
        gameId = RandomGenerator.getInstance().getUUID();
        gameConfiguration = new GameConfiguration();
        maxPlayerCount = gameConfiguration.getPlayerCount();
        init();
    }

    @Override
    public void init() {
        playerCells = new RotatingList<>();
        scoreTracker = new ColorifyScoreTracker(gameConfiguration);
        state = GameState.WAITING_FOR_PLAYERS_TO_JOIN;
    }

    @Override
    public String addPlayer(String playerId) throws IllegalStateError {
        if (GameState.ALL_PLAYER_JOINED.equals(state)) {
            start();
            state = GameState.START;
        }
        if (GameState.START.equals(state)) {
            return state.getValue();
        }
        if (!GameState.WAITING_FOR_PLAYERS_TO_JOIN.equals(state)) {
            throw new IllegalStateError("game not in desired state for adding player");
        }
        for (CellCoordinate coordinate : playerCells) {
            String id = coordinate.getPlayerId();
            if (id.equals(playerId))
                return state.getValue();
        }
        CellCoordinate coordinate = new CellCoordinate();
        coordinate.setPlayerId(playerId);
        playerCells.add(coordinate);
        if (playerCells.size() == maxPlayerCount) {
            state = GameState.ALL_PLAYER_JOINED;
            start();
        }
        return state.getValue();
    }

    @Override
    public String start() throws IllegalStateError {
        if (!GameState.ALL_PLAYER_JOINED.equals(state))
            throw new IllegalStateError("game not in desired state:");
        else state = GameState.START;

        movesSoFar = 0;
        assignCoordinates();
        populateScoreTracker();
        return state.getValue();
    }

    @Override
    public void makeMove(String playerId, Cell newCell) throws IllegalMoveException {
        CellCoordinate coordinate = findPlayerCoordinate(playerId);

        FloodFill floodFill = new FloodFill();

        floodFill.floodFill(board, coordinate.getR(), coordinate.getC(), board.getCell(coordinate.getR(), coordinate.getC()), newCell);

        int count = floodFill.countFill(board, coordinate.getR(), coordinate.getC(), newCell);
        movesSoFar++;
        updatePalette();
        updateScoreTracker(coordinate.getPlayerId(), count);
        rotatePlayerChance();
        checkFinish();
    }

    private void updatePalette() {
        palette = new ColorifyPalette(board, gameConfiguration); // todo: Bad practice to create a whole new Object. modify existing.
    }

    private void updateScoreTracker(String playerId, int count) {
        scoreTracker.updateScore(playerId, count);
    }

    public List<String> getPlayerIds() {
        return playerCells.stream().map(CellCoordinate::getPlayerId).collect(Collectors.toList());
    }

    private CellCoordinate findPlayerCoordinate(String playerId) throws IllegalMoveException {
        for (CellCoordinate coordinate : playerCells)
            if (coordinate.getPlayerId().equals(playerId))
                return coordinate;
        throw new IllegalMoveException("player not found" + playerId);
    }


    @Override
    public boolean checkFinish() {
        for (String playerId : getPlayerIds())
            if (scoreTracker.getPercentScoreByPlayer(playerId) >= 50) {
                state = GameState.FINISH;
                return true;
            }
        return false;
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

    @Override
    protected void rotatePlayerChance() {
        playerCells.rotateNext();
    }

    private void populateScoreTracker() {
        FloodFill floodFill = new FloodFill();
        for (CellCoordinate coordinate : playerCells) {
            int count1 = floodFill.countFill(board, coordinate.getR(), coordinate.getC(), board.getCell(coordinate.getR(), coordinate.getC()));
            scoreTracker.updateScore(coordinate.getPlayerId(), count1);
        }
    }

    private void assignCoordinates() {
        // 2Player Game.
        playerCells.get(0).setR(0);
        playerCells.get(0).setC(0);

        playerCells.get(1).setR(board.getRows() - 1);
        playerCells.get(1).setC(board.getCols() - 1);

        shufflePlayerCells();
    }

    private void shufflePlayerCells() {
        Collections.shuffle(playerCells);
    }

    public boolean isPlayerChance(String playerId) {
        if (playerId == null) return false;
        return playerId.equals(getPlayerChance());
    }

    public String getPlayerChance() {
        if (playerCells == null || playerCells.peekFirst() == null)
            return null;
        else
            return playerCells.peekFirst().getPlayerId();

    }
}
