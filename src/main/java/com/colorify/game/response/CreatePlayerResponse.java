package com.colorify.game.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePlayerResponse extends Response {
    private String playerId;
}
