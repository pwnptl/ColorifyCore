package com.colorify.game.mechanics.Strategies;

import java.util.LinkedList;

public class RotatingList<T> extends LinkedList<T> {
    public void rotateNext() {
        if (!isEmpty()) {
            T element = removeFirst();
            addLast(element);
        }
    }
}