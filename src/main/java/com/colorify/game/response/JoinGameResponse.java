package com.colorify.game.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinGameResponse extends Response {
    private final String gameId;
    private final boolean isJoined;
}
