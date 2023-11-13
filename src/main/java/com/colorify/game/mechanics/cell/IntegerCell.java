package com.colorify.game.mechanics.cell;

import com.platform.core.game.Cell;

import java.io.Serializable;

public class IntegerCell implements Cell, Serializable {
    private int value;

    public IntegerCell(final int cell) {
        this.value = cell;
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
