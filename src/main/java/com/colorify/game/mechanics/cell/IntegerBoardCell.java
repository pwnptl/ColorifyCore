package com.colorify.game.mechanics.cell;

import com.platform.core.utility.RandomGenerator;
import lombok.Getter;

public class IntegerBoardCell implements BoardCell {
    @Getter
    private int cell;

    public IntegerBoardCell(final int modulo) {
        cell = RandomGenerator.getInstance().getRandNumber(modulo);
    }
}
