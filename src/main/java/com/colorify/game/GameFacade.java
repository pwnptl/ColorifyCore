package com.colorify.game;

import com.colorify.game.mechanics.BaseGame;
import com.platform.core.database.AbstractDatabase;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.player.HumanPlayer;
import com.platform.core.player.Player;
import lombok.NonNull;

public class GameFacade {

    final AbstractDatabase database = AbstractDatabase.getInstance();

    public String initGame() {
        AbstractBaseGame baseGame = new BaseGame();

        database.putGame(baseGame.getId(), baseGame);
        return baseGame.toString();
    }

    public String createPlayer(@NonNull String name) {
        Player player = new HumanPlayer(name);
        database.putPlayer(player.getId(), player);
        return player.getId();
    }
}
