package com.colorify.game.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateGameResponse extends Response {
    private final String gameId;
    private final String status;
}