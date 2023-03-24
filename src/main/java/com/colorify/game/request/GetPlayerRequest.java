package com.colorify.game.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPlayerRequest extends Request {
    private final String playerId;
}
