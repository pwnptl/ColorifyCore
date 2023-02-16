package com.colorify.game;

import com.colorify.game.mechanics.BaseFacade;
import com.platform.core.database.AbstractDatabase;
import com.platform.core.player.HumanPlayer;
import com.platform.core.player.Player;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PlayerFacade extends BaseFacade {

    private final AbstractDatabase database;

    public PlayerFacade() {
        database = AbstractDatabase.getInstance(getTypeAdapters());
    }

    public String createPlayer(@NonNull String name) {
        Player player = new HumanPlayer(name);
        database.putPlayer(player.getPlayerId(), player);
        return player.getPlayerId();
    }

    public Player getPlayer(@NonNull String playerId){
        Player data = database.queryPlayer(playerId);
        return data;
    }
}
