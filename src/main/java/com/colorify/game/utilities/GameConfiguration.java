package com.colorify.game.utilities;

public class GameConfiguration {
    public int getNumberOfPlayers() {
        return Constants.DEFAULT_PLAYER_COUNT;
    }
    public int get() {
        return Constants.DEFAULT_PLAYER_COUNT;
    }


    public int getPaletteColourCount() {
        return getColourCount() - getNumberOfPlayers();
    }

    public int getColourCount() {
        return Constants.DEFAULT_BOARD_COLORS;
    }
}
