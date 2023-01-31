package com.platform.core.stateMachine;

import com.platform.core.errors.IllegalStateError;
import com.platform.core.errors.NotImplementedError;
import com.platform.core.testUtilities.DummyClass;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class StateMachineTest {

    @Test
    void testAddStates() throws IllegalStateError {
        new TestRunner()
                .newStateMachine()
                .addState("methodA")
                .addState("methodB")
                .verifyStateCount(2);
    }

    @Test
    void testAddStates_throwException() {
        // todo its better to expect exception.
        Exception e = null;
        try {
            new TestRunner()
                    .newStateMachine()
                    .addState("methodA")
                    .addState("methodA");
        } catch (IllegalStateError illegalStateError) {
            e = illegalStateError;
        }
        assertNotNull(e);
    }

    @Test
    void testStart() throws IllegalStateError, NotImplementedError, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        new TestRunner()
                .newStateMachine()
                .addState("methodA")
                .addState("methodB", true)
                .verifyStateCount(2)
                .startMachine()
                .verifyAnyMethodInvoked();
    }


    private class TestRunner {

        /**
         * Dummy Class for primaryObject in StateMachine.
         */


        private DummyClass dummyObject;
        private StateMachine stateMachine;

        public TestRunner newStateMachine() {
            dummyObject = new DummyClass();
            stateMachine = new StateMachine(dummyObject);
            dummyObject.setStateMachine(stateMachine);
            return this;
        }


        public TestRunner addState(String methodName) throws IllegalStateError {
            return addState(methodName, false);
        }

        public TestRunner addState(String methodName, boolean terminalState) throws IllegalStateError {
            stateMachine.addState(methodName);
            if(terminalState)
                stateMachine.setTerminalState(methodName);
            return this;
        }

        public TestRunner verifyStateCount(int count) {
            assertEquals(stateMachine.getStates().size(), count);
            return this;
        }

        public TestRunner startMachine() throws NotImplementedError, IllegalStateError, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            stateMachine.start();
            return this;
        }

        public void verifyAnyMethodInvoked() {
            assertTrue(dummyObject.isMethodAInvoked() || dummyObject.isMethodBInvoked());
        }
    }
}