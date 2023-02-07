package com.colorify.game.mechanics.cell;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.BoardCell;
import com.platform.core.utility.RandomGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegerBoardCell implements BoardCell {
    private int cell;

    public IntegerBoardCell(final int cell) {
        this.cell = cell;
    }

    public IntegerBoardCell(GameConfiguration gameConfiguration) {
        int modulo = gameConfiguration.getColourCount();
        cell = RandomGenerator.getInstance().getRandNumber(modulo);
    }
}
