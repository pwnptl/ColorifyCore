package com.platform.core.stateMachine;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class State {
    protected String name;

    protected List<Object> args;

    protected boolean isTerminalState;

    public State(String name) {
        this.name = name;
        isTerminalState = false;
    }

    public State(String name, List<Object> args) {
        this.name = name;
        this.args = args;
        this.isTerminalState = false;
    }

    public void setTerminalState() {
        this.isTerminalState = true;
    }
}