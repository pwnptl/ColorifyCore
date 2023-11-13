package com.colorify.game.utilities;

import lombok.Getter;

import java.io.Serializable;

@Getter
public final class GameConfiguration implements Serializable {

    int playerCount;
    int paletteColourCount;

    int colourCount;
    int rows;
    int columns;

    public GameConfiguration() {
        this.playerCount = Constants.DEFAULT_PLAYER_COUNT;
        this.colourCount = Constants.DEFAULT_BOARD_COLORS;
        this.paletteColourCount = colourCount - playerCount;
        this.rows = Constants.DEFAULT_BOARD_ROWS;
        this.columns = Constants.DEFAULT_BOARD_COLS;
    }

    public GameConfiguration(int r, int c, int playerCount, int colourCount) {
        this.playerCount = playerCount;
        this.colourCount = colourCount;
        this.paletteColourCount = this.colourCount - this.playerCount;
        this.rows = r;
        this.columns = c;
    }

}
