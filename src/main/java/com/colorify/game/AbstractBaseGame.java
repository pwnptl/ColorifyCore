package com.colorify.game;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.stateMachine.StateMachine;

public abstract class AbstractBaseGame {

    protected StateMachine stateMachine;
    protected GameConfiguration gameConfiguration;

    public abstract void initGame();

    public abstract void addStates();
    public abstract void addPlayer(String name);

    public abstract void startGame();
    public abstract void nextMove();
    public abstract void finishGame();


}
