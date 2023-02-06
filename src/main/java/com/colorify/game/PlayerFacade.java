package com.colorify.game;

import com.platform.core.database.AbstractDatabase;
import com.platform.core.player.HumanPlayer;
import com.platform.core.player.Player;
import lombok.NonNull;

public class PlayerFacade {

    final AbstractDatabase database = AbstractDatabase.getInstance();

    public String createPlayer(@NonNull String name) {
        Player player = new HumanPlayer(name);
        database.putPlayer(player.getId(), player);
        return player.getId();
    }
}
