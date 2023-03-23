package com.colorify.game.response;

import com.platform.core.player.Player;
import com.platform.core.player.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPlayerResponse extends Response {

    protected String playerId;
    protected String name;
    protected PlayerType type;

    public GetPlayerResponse(final Player player){
        this.name = player.getName();
        this.type = player.getType();
        this.playerId = player.getPlayerId();
    }
}
