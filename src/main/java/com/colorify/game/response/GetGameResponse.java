package com.colorify.game.response;

import com.colorify.colorify.model.responseBuilder.GameDataResponse;
import com.colorify.game.mechanics.board.Board;
import com.platform.core.game.GameState;
import com.platform.core.game.Palette;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
public class GetGameResponse extends Response {

    private final String requesterPlayerId;
    private final String gameId;
    private final int totalPossiblePlayerCount;
    private final ArrayList<String> currentPlayerIds;

    private final GameState gameState;

    private final Board board;
    private final Palette palette;


    public GetGameResponse(String playerId, GameDataResponse gameDataResponse) {
        this.requesterPlayerId = playerId;
        this.gameId = gameDataResponse.getGameId();
        this.totalPossiblePlayerCount = gameDataResponse.getMaxPlayerCount();
        this.currentPlayerIds = gameDataResponse.getPlayerList();
        this.gameState = gameDataResponse.getState();
        this.board = gameDataResponse.getBoard();
        this.palette = gameDataResponse.getPalette();
    }

}