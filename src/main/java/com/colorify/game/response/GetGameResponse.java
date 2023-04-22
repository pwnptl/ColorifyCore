package com.colorify.game.response;

import com.colorify.colorify.model.responseBuilder.GameDataResponse;
import com.colorify.game.mechanics.board.Board;
import com.platform.core.game.GameState;
import com.platform.core.game.Palette;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Setter
@AllArgsConstructor
public class GetGameResponse extends Response {

    private final String requesterPlayerId;
    private final Data data;

    public GetGameResponse(String playerId, GameDataResponse gameDataResponse) {
        this.requesterPlayerId = playerId;
        this.data = new Data(gameDataResponse.getGameId(),
                gameDataResponse.getMaxPlayerCount(),
                gameDataResponse.getPlayerList(),
                gameDataResponse.getState(),
                gameDataResponse.getBoard(),gameDataResponse.getPalette());
    }

    @Getter
    @AllArgsConstructor
    class Data {
        private final String gameId;
        private final int totalPossiblePlayerCount;
        private final ArrayList<String> currentPlayerIds;

        private final GameState gameState;

        private final Board board;
        private final Palette palette;

    }
}

