package com.colorify.game.utilities;

public final class Constants {

    public static final int DEFAULT_PLAYER_COUNT = 2;

    private Constants() {
    }

    public final static int DEFAULT_BOARD_ROWS = 10;
    public final static int DEFAULT_BOARD_COLS = 10;
    public static final int DEFAULT_BOARD_COLORS = 6;

    public final class OperationStatus {
        private OperationStatus() {}
        public static final String CANNOT_ADD_DUPLICATE_PLAYER = "CANNOT_ADD_DUPLICATE_PLAYER";

    }

    public static class ApiMessageTypes {

        public static final String PLAYER_DATA = "PLAYER_DATA";
    }
}
