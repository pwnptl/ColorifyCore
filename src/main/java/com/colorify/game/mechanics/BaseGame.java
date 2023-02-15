package com.colorify.game.mechanics;

import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import com.colorify.game.mechanics.scoreTracker.ColorifyScoreTracker;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.errors.IllegalStateError;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.game.Cell;
import com.platform.core.game.GameState;
import com.platform.core.game.ScoreTracker;
import com.platform.core.player.Player;
import com.platform.core.utility.RandomGenerator;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class BaseGame extends AbstractBaseGame {
    private Board board;
    private ColorifyPalette palette;
    private ScoreTracker scoreTracker;
    private ArrayList<String> playerIds;
    private ArrayList<Cell> playerCells;
    private GameState state;

    private BaseGameHistory history;

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
        playerIds = new ArrayList<>();
        scoreTracker = new ColorifyScoreTracker(maxPlayerCount);
        state = GameState.WAITING_FOR_PLAYERS_TO_JOIN;
        history = new BaseGameHistory(this);
        playerCells = new ArrayList<>();
    }

    @Override
    public String addPlayer(String playerId) throws IllegalStateError {
        if (!GameState.WAITING_FOR_PLAYERS_TO_JOIN.equals(state)) {
            throw new IllegalStateError("game not in desired state for adding player");
        }
        for (String id : playerIds)
            if (id.equals(playerId))
                throw new IllegalArgumentException("Player Already Present");
        playerIds.add(playerId);
        if (playerIds.size() == maxPlayerCount) {
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
    public void makeMove(Player player, Cell moveNo) {
        history.saveMove(player, moveNo);


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

    }

    private void assignCoordinates() {
        playerCells.add(board.getCell(0, 0));
        playerCells.add(board.getCell(board.getRows() - 1, board.getCols() - 1));
    }


}
