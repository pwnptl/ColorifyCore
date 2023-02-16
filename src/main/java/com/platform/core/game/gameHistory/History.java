package com.platform.core.game.gameHistory;

import com.platform.core.game.AbstractBaseGame;
import com.platform.core.game.Cell;
import com.platform.core.player.Player;

public abstract class History {
    public History(AbstractBaseGame abstractBaseGame) {
    }

    public History() {
    }

    public abstract void initiateGame();

    public abstract void saveMove(Player player, Cell cell);

}
