package com.colorify.game.mechanics.cell;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.Cell;
import com.platform.core.utility.RandomGenerator;

public class CellBuilder {
    public Cell getIntegerCell(final GameConfiguration gameConfiguration)
    {
        int modulo = gameConfiguration.getColourCount();
        return new IntegerCell(RandomGenerator.getInstance().getRandNumber(modulo));
    }
}
