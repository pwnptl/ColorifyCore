package com.colorify.game.mechanics;

import com.colorify.game.AbstractBaseGame;
import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.Palette;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.player.Player;
import com.platform.core.player.PlayerBuilder;
import com.platform.core.stateMachine.StateMachine;

import java.util.ArrayList;

public class BaseGame extends AbstractBaseGame {
    private Board board;
    private Palette palette;

    private ArrayList<Player> players;

    private int maxPlayerCount;

    @Override
    public void initGame() {
        gameConfiguration = new GameConfiguration();
        board = new Board();
        palette = new Palette(gameConfiguration);
        maxPlayerCount = gameConfiguration.getNumberOfPlayers();
        players = new ArrayList<>();
        stateMachine = new StateMachine(this);
    }

    @Override
    public void addStates() {

    }

    @Override
    public void addPlayer(String name) {

        PlayerBuilder playerBuilder = new PlayerBuilder();
        Player player = playerBuilder.buildHuman(name);
        players.add(player);
    }

    @Override
    public void startGame() {

    }

    @Override
    public void nextMove() {

    }

    @Override
    public void finishGame() {

    }
}
