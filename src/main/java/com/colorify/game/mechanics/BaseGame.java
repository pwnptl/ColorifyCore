package com.colorify.game.mechanics;

import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.game.ScoreTracker;
import com.platform.core.utility.RandomGenerator;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class BaseGame extends AbstractBaseGame {
    @Getter
    private Board board;
    @Getter
    private ColorifyPalette palette;

    @Getter private ScoreTracker scoreTracker;
    @Getter
    private ArrayList<String> playerIds;

    private int maxPlayerCount;

    public BaseGame() {
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
    }

    @Override
    public void addPlayer(String playerId) {
        playerIds.add(playerId);
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
