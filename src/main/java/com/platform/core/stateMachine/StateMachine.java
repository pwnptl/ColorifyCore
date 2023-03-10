package com.platform.core.stateMachine;

import com.platform.core.errors.IllegalStateError;
import com.platform.core.errors.NotImplementedError;
import com.platform.core.utility.Logger;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StateMachine {
    private final Logger logger = new Logger();
    @Getter
    private final ArrayList<State> states;

    private final Object primarySubject;

    @Getter
    private int currentStateIndex;

    public StateMachine(Object primarySubject) {
        states = new ArrayList<>();
        this.primarySubject = primarySubject;
        currentStateIndex = 0;
    }


    public void addState(@NonNull final String name, final Object... args) throws IllegalStateError {
        verifyUniqueStateName(name);
        State newstate = new StandaloneState(name, List.of(args));
        states.add(newstate);
    }

    public void start() throws IllegalStateError, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NotImplementedError {
        verifyTerminalState();
        startState(0);
    }

    public void next() throws NotImplementedError, InvocationTargetException, NoSuchMethodException, IllegalAccessException, IllegalStateError {
        startState(currentStateIndex + 1);
    }

    public void next(String nextStateName) throws NotImplementedError, InvocationTargetException, NoSuchMethodException, IllegalAccessException, IllegalStateError {
        int i;
        for (i = 0; i < states.size(); ++i) {
            if (states.get(i).getName().equals(nextStateName))
                break;
        }
        if (i >= states.size())
            throw new IllegalStateError("No such State " + nextStateName);

        startState(i);
    }


    private void startState(int index) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NotImplementedError, IllegalStateError {
        if (states.get(currentStateIndex).isTerminalState())
            return;

        if (index >= states.size()) {
            throw new IllegalStateError("State Index Out of Bound " + index);
        }
        currentStateIndex = index;
        startState(states.get(index));
    }

    private void startState(State state) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NotImplementedError {
        Method method = primarySubject.getClass().getMethod(state.getName());
        logger.info("invoking state : " + method.getName());
        invoke(method, state.getArgs());
    }

    private void invoke(Method method, List<Object> args) throws InvocationTargetException, IllegalAccessException, NotImplementedError {
        switch (args.size()) {
            case 0 -> method.invoke(primarySubject);
            case 1 -> method.invoke(primarySubject, args.get(0));
            case 2 -> method.invoke(primarySubject, args.get(0), args.get(1));
            case 3 -> method.invoke(primarySubject, args.get(0), args.get(1), args.get(2));
            default -> throw new NotImplementedError("invoke method with args: " + args.size());
        }
    }

    public void setTerminalState(String methodName) {
        for (State state : states)
            if (state.getName().equals(methodName))
                state.setTerminalState(true);
    }


    private void verifyUniqueStateName(String name) throws IllegalStateError {
        for (State state : states)
            if (state.getName().equals(name))
                throw new IllegalStateError("State Already exist");
    }

    private void verifyTerminalState() throws IllegalStateError {
        for (State state : states)
            if (state.isTerminalState())
                return;
        throw new IllegalStateError("No Terminal State in Machine");
    }


}
