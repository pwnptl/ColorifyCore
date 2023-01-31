package com.colorify.game.mechanics.cell;

import com.colorify.game.utilities.RandomGenerator;

public class IntegerBoardCell implements BoardCell {
    int cell;

    public IntegerBoardCell(final int modulo) {
        cell = RandomGenerator.getRandNumber() % modulo;
    }
}