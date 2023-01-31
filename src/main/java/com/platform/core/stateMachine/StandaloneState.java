package com.platform.core.stateMachine;

import java.util.List;

public class StandaloneState extends State {
    public StandaloneState(String name, List<Object> args) {
        super(name, args);
    }
    public StandaloneState(String name) {
        super(name);
    }
}
