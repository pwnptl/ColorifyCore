package com.colorify.game.mechanics.cell;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Cell;
import com.platform.core.utility.RandomGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegerCell implements Cell {
    private int cell;
    private int r;
    private int c;

    public IntegerCell(final int cell) {
        this.cell = cell;
    }

    public IntegerCell(GameConfiguration gameConfiguration) {
        int modulo = gameConfiguration.getColourCount();
        cell = RandomGenerator.getInstance().getRandNumber(modulo);
    }

    @Override
    public void setCoordinate(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int getCellValue()
    {
        return cell;
    }
}
