package com.platform.core.testUtilities;

import com.platform.core.errors.IllegalStateError;
import com.platform.core.errors.NotImplementedError;
import com.platform.core.stateMachine.StateMachine;

import java.lang.reflect.InvocationTargetException;

public class DummyClass {

    private StateMachine stateMachine;
    private boolean methodAInvoked;
    private boolean methodBInvoked;


    public DummyClass() {
        methodAInvoked = false;
        methodBInvoked = false;
    }

    public boolean isMethodAInvoked(){
        return methodAInvoked;
    }
    public boolean isMethodBInvoked(){
        return methodBInvoked;
    }
    public void methodA() throws NotImplementedError, IllegalStateError, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        methodAInvoked = true;
        stateMachine.next();
    }

    public void methodB() throws NotImplementedError, IllegalStateError, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        methodBInvoked = true;
        stateMachine.next();
    }

    public void setStateMachine(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }
}