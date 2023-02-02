package com.platform.core.stateMachine;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
public abstract class State {
    @NonNull
    private String name;

    private List<Object> args;

    @Setter
    private boolean terminalState;

    public State(final String name) {
        this.name = name;
        terminalState = false;
    }

    public State(final String name, final List<Object> args) {
        this.name = name;
        this.args = args;
        this.terminalState = false;
    }

    @Override
    public String toString() {
        String s = name;
        if(terminalState)
            s+="::TERMINAL";
        return s;
    }
}