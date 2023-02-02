package com.colorify.game.mechanics.cell;

import com.platform.core.utility.RandomGenerator;
import lombok.Getter;

public class IntegerBoardCell implements BoardCell {

    private static RandomGenerator randomGenerator = new RandomGenerator();
    @Getter
    private int cell;

    public IntegerBoardCell(final int modulo) {
        cell = randomGenerator.getRandNumber(modulo);
    }
}
