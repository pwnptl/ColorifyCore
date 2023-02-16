package com.colorify.colorify.controller.errors;

public class IllegalMoveException extends Exception {
    public IllegalMoveException(String s) {
        super(s);
    }

    public IllegalMoveException() {
        super();
    }
}
