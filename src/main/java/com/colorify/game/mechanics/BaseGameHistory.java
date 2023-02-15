package com.colorify.game.mechanics;

import com.platform.core.game.AbstractBaseGame;
import com.platform.core.game.Cell;
import com.platform.core.game.gameHistory.History;
import com.platform.core.player.Player;

import java.util.ArrayList;

public class BaseGameHistory extends History {
    private AbstractBaseGame initialGame;

    private ArrayList<Player> playerHistory;
    private ArrayList<Cell> moveHistory;

    public BaseGameHistory(BaseGame baseGame) {
        super();
        initialGame = baseGame;
    }

    @Override
    public void initiateGame(AbstractBaseGame baseGame) {
        this.initialGame = baseGame;
        this.moveHistory = new ArrayList<>();
        this.playerHistory = new ArrayList<>();
    }

    public void saveMove(Player player, Cell cell)
    {
        this.playerHistory.add(player);
        this.moveHistory.add(cell);
    }
}
