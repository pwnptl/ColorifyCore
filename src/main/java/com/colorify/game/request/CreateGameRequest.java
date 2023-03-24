package com.colorify.game.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateGameRequest extends Request {
    private final String currentPlayerId;
}
