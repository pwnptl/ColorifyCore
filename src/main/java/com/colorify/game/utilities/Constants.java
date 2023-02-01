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
            add("gameInit");
            add("WaitPlayerToJoin");
            add("startGame");
            add("makeMove");
            add("waitForOtherPlayerMove");

            add("validateCurrentGame");
            add("awarding");
            add("terminateGame");

        }
    };
}
