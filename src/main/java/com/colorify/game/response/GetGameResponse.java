package com.colorify.game.response;

import com.colorify.colorify.model.responseBuilder.GameDataResponse;
import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import com.platform.core.game.GameState;
import com.platform.core.game.ScoreTracker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
public class GetGameResponse extends Response {

    protected final String requesterPlayerId;
    protected final String gameId;
    protected final int totalPossiblePlayerCount;
    protected final ArrayList<String> currentPlayerIds;

    protected final GameState gameState;

    protected final Board board;
    protected final ColorifyPalette palette;

    protected final ScoreTracker scoreTracker;


    public GetGameResponse(final String playerId, final GameDataResponse gameDataResponse) {
        this.requesterPlayerId = playerId;
        this.gameId = gameDataResponse.getGameId();
        this.totalPossiblePlayerCount = gameDataResponse.getMaxPlayerCount();
        this.currentPlayerIds = gameDataResponse.getPlayerList();
        this.gameState = gameDataResponse.getState();
        this.board = gameDataResponse.getBoard();
        this.palette = gameDataResponse.getPalette();
        this.scoreTracker = gameDataResponse.getScoreTracker();
    }

}