package com.colorify.game.utilities;

import java.util.ArrayList;
import java.util.Collections;

public final class Constants {

    public static final int DEFAULT_PLAYER_COUNT = 2;

    private Constants() {
    }

    public final static int DEFAULT_BOARD_ROWS = 10;
    public final static int DEFAULT_BOARD_COLS = 10;
    public static final int DEFAULT_BOARD_COLORS = 6;

    public static final ArrayList<String> states = new ArrayList<>() {
        {
            // refer AbstractBaseGame class.
            add("init");
            add("addPlayer");
            add("start");
            add("makeMove");
            add("waitForOpponent");
            add("validate");
            add("finishGame");
            add("awards");
        }
    };
}
