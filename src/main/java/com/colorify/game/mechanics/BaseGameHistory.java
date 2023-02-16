package com.colorify.game.mechanics;

import com.platform.core.game.Cell;
import com.platform.core.game.gameHistory.History;
import com.platform.core.player.Player;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class BaseGameHistory extends History {
    //    private AbstractBaseGame initialGame;
    private ArrayList<Player> playerHistory;
    private ArrayList<Cell> moveHistory;


    public BaseGameHistory() {
        super();
        initiateGame();
    }

    @Override
    public void initiateGame() {
        this.moveHistory = new ArrayList<>();
        this.playerHistory = new ArrayList<>();
    }

    public void saveMove(Player player, Cell cell)
    {
        this.playerHistory.add(player);
        this.moveHistory.add(cell);
    }
}
