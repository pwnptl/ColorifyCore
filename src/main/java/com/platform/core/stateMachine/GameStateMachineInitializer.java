package com.platform.core.stateMachine;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.errors.IllegalStateError;
import com.platform.core.errors.NotImplementedError;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.utility.Logger;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;

public class GameStateMachineInitializer {
    @Getter // getter only for unit test.
    protected StateMachine stateMachine;

    protected static GameConfiguration gameConfiguration = new GameConfiguration();

    public void init(final AbstractBaseGame baseGame) throws IllegalStateError {
        stateMachine = new StateMachine(baseGame);
        setStates();
        Logger.info("Machine initialised with states " + stateMachine.getStates());
    }

    public void startMachine() throws IllegalStateError, NotImplementedError, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        stateMachine.start();
    }

    private void setStates() throws IllegalStateError {
//        for (String stateName : gameConfiguration.getStates()) {
//            stateMachine.addState(stateName);
//        }
//        stateMachine.setTerminalState(Constants.states.get(Constants.states.size() - 1));
    }
}
