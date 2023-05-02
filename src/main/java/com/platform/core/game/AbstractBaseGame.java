package com.platform.core.game;

import com.colorify.colorify.controller.errors.IllegalMoveException;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.errors.IllegalStateError;
import lombok.Getter;

@Getter
public abstract class AbstractBaseGame {
    protected String gameId;
    protected GameConfiguration gameConfiguration;

    public abstract void init();

    public abstract String addPlayer(String name) throws IllegalStateError;

    public abstract String start() throws IllegalStateError;

    public abstract void makeMove(String player, Cell moveNo) throws IllegalMoveException;

    public abstract boolean checkFinish();

    public abstract void finish();

    public abstract void awards();

    public abstract void terminate();

    public abstract void rotatePlayerChance();
}
