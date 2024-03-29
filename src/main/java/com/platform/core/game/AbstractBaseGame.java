package com.platform.core.game;

import com.colorify.colorify.controller.errors.IllegalMoveException;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.errors.IllegalStateError;
import lombok.Getter;

import java.io.Serializable;

@Getter
public abstract class AbstractBaseGame implements Serializable {
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

    protected abstract void rotatePlayerChance();
}
