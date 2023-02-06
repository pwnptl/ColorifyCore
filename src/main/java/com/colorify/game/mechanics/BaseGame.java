package com.colorify.game.mechanics;

import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import com.colorify.game.mechanics.scoreTracker.ColorifyScoreTracker;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.errors.IllegalStateError;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.game.GameState;
import com.platform.core.game.ScoreTracker;
import com.platform.core.utility.RandomGenerator;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class BaseGame extends AbstractBaseGame {
    private Board board;
    private ColorifyPalette palette;
    private ScoreTracker scoreTracker;
    private ArrayList<String> playerIds;
    private GameState state;

    private int maxPlayerCount;

    public BaseGame() {
        state = GameState.NOT_INITIALIZED;
        init();
    }

    @Override
    public void init() {
        id = RandomGenerator.getInstance().getUUID();
        gameConfiguration = new GameConfiguration();
        board = new Board(gameConfiguration);
        palette = new ColorifyPalette(gameConfiguration);
        maxPlayerCount = gameConfiguration.getNumberOfPlayers();
        playerIds = new ArrayList<>();
        scoreTracker = new ColorifyScoreTracker(maxPlayerCount);
        state = GameState.WAITING_FOR_PLAYERS_TO_JOIN;
    }

    @Override
    public String addPlayer(String playerId) throws IllegalStateError {
        if (!GameState.WAITING_FOR_PLAYERS_TO_JOIN.equals(state)) {
            throw new IllegalStateError("game not in desired state for adding player");
        }
        for(String id: playerIds)
            if(id.equals(playerId))
                throw new IllegalArgumentException("Player Already Present");
        playerIds.add(playerId);
        if (playerIds.size() == maxPlayerCount) {
            state = GameState.ALL_PLAYER_JOINED;
        }
        return state.getValue();
    }

    @Override
    public void start() {

    }

    @Override
    public void makeMove() {

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
}
