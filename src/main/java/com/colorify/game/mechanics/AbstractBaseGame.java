package com.colorify.game.mechanics;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.stateMachine.StateMachine;
import lombok.Getter;


public abstract class AbstractBaseGame {

    protected StateMachine stateMachine;
    protected GameConfiguration gameConfiguration;

    public abstract String getData();

    public abstract void init();

    public abstract void addPlayer(String name);

    public abstract void start();

    public abstract void makeMove();

    public abstract void waitForOpponent();

    public abstract void validate();

    public abstract void finish();

    public abstract void awards();

    public abstract void terminate();

}
