package com.platform.core.utility;

public final class Constants {
    private Constants() {
    }

    public final class DBConstants {
        public static final String FILE_NAME = "store_file";
        public static String DB_URI = "mongodb://localhost:27017";
        public static String DB_NAME = "Colorify";
        public static String PLAYER_TABLE_NAME = "User";
        public static String GAME_COLLECTION_NAME = "Game";

        public static String _id = "_id";
        public static String _data = "data";
    }
}
