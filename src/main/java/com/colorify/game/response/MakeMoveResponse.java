package com.colorify.game.response;

import com.colorify.colorify.model.responseBuilder.GameDataResponse;
import lombok.Getter;

@Getter
public class MakeMoveResponse extends GetGameResponse { // both responses are essentially same.
    public MakeMoveResponse(String playerId, GameDataResponse gameDataResponse) {
        super(playerId, gameDataResponse);
    }
}
