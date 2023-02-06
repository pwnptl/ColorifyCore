package com.colorify.colorify.model.responseBuilder;

import com.colorify.game.mechanics.BaseGame;
import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class InitGameResponse {

    private final String gameId;
    private final int currentPlayerCount;
    private final int maxPlayerCount;
    private final Board board;
    private final ColorifyPalette palette;
    private final ArrayList<String> getPlayers;

    public InitGameResponse(final BaseGame baseGame) {
        this.gameId = baseGame.getId();
        this.currentPlayerCount = baseGame.getPlayerIds().size();
        this.maxPlayerCount = baseGame.getMaxPlayerCount();
        this.board = baseGame.getBoard();
        this.palette = baseGame.getPalette();
        this.getPlayers = baseGame.getPlayerIds();

    }
}
