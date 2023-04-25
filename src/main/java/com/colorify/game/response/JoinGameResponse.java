package com.colorify.game.response;

import com.platform.core.game.GameState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class JoinGameResponse extends Response {
    private final String gameId;
    private final boolean joined;
    private ArrayList<String> joinedPlayers;
    private GameState gameState;
}
