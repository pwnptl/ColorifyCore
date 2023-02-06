package com.colorify.game;

import com.platform.core.game.AbstractBaseGame;
import com.colorify.game.mechanics.BaseGame;
import com.platform.core.errors.IllegalStateError;
import com.platform.core.stateMachine.GameStateMachineInitializer;
import com.platform.core.utility.Logger;
import org.junit.jupiter.api.Test;

class GameStateMachineInitializerTest {

    @Test
    public void test_init() throws IllegalStateError {
        new TestRunner()
                .newGameInit()
                .runInit()
                .printStateMachine();
    }

    private class TestRunner
    {
        private GameStateMachineInitializer gameInitializer;
        private AbstractBaseGame baseGame;

        public TestRunner newGameInit() {
            baseGame = new BaseGame();
            gameInitializer = new GameStateMachineInitializer();
            return this;
        }

        public TestRunner runInit() throws IllegalStateError {
            gameInitializer.init(baseGame);
            return this;
        }

        public void printStateMachine() {
            Logger.info(gameInitializer.getStateMachine().getStates().toString());
        }
    }

}