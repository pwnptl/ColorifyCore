package com.platform.core.game;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.errors.IllegalStateError;
import lombok.Getter;

@Getter
public abstract class AbstractBaseGame {
    protected String id;
    protected GameConfiguration gameConfiguration;

    public abstract void init();

    public abstract String addPlayer(String name) throws IllegalStateError;

    public abstract void start();

    public abstract void makeMove();

    public abstract void waitForOpponent();

    public abstract void validate();

    public abstract void finish();

    public abstract void awards();

    public abstract void terminate();

}
