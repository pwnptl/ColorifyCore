package com.colorify.game.mechanics;

import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.Palette;
import com.colorify.game.mechanics.player.ColorifyPlayerBuilder;
import com.colorify.game.utilities.GameConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.player.Player;
import com.platform.core.player.PlayerBuilder;
import com.platform.core.stateMachine.StateMachine;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import com.platform.core.utility.RandomGenerator;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class BaseGame extends AbstractBaseGame {
    private static Logger logger = new Logger();
    @Getter
    private Board board;
    @Getter
    private Palette palette;

    @Getter
    private ArrayList<Player> players;

    private int maxPlayerCount;

    @Override
    public String getData() {
        try {
            return ObjectJsonConverter.toJSON(board);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() {
        id = RandomGenerator.getInstance().getUUID();
        gameConfiguration = new GameConfiguration();
        board = new Board(gameConfiguration);
        palette = new Palette(gameConfiguration);
        maxPlayerCount = gameConfiguration.getNumberOfPlayers();
        players = new ArrayList<>();
        stateMachine = new StateMachine(this);
    }

    @Override
    public void addPlayer(String name) {

        PlayerBuilder playerBuilder = new ColorifyPlayerBuilder();
        Player player = playerBuilder.buildHuman(name);
        players.add(player);
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
