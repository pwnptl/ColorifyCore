package com.colorify.game.response;

import com.colorify.colorify.model.responseBuilder.GameDataResponse;
import lombok.AllArgsConstructor;
import lombok.Setter;


@Setter
@AllArgsConstructor
public class GetGameResponse extends Response {

    private final String playerId;
    private final GameDataResponse gameDataResponse;

}
