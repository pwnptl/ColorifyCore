package com.colorify.game.mechanics.cell;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Cell;
import com.platform.core.utility.RandomGenerator;

public class IntegerCell implements Cell {
    private int value;

    public IntegerCell(final int cell) {
        this.value = cell;
    }

    public IntegerCell(GameConfiguration gameConfiguration) {
        int modulo = gameConfiguration.getColourCount();
        value = RandomGenerator.getInstance().getRandNumber(modulo);
    }

    @Override
    public void setCell(int cell) {
        this.value = cell;
    }

    @Override
    public int getCell() {
        return value;
    }
}
