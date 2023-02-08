package com.colorify.colorify.model.responseBuilder;

import com.colorify.game.mechanics.BaseGame;
import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import com.platform.core.game.GameState;
import com.platform.core.game.ScoreTracker;
import com.platform.core.player.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GameDataResponse {

    private final String gameId;
    private final int currentPlayerCount;
    private final int maxPlayerCount;
    private final Board board;
    private final ColorifyPalette palette;
    private final ScoreTracker scoreTracker;
    private final GameState state;
    private final List<Player> players;

    public GameDataResponse(final BaseGame baseGame) {
        this.gameId = baseGame.getGameId();
        this.currentPlayerCount = baseGame.getPlayerIds().size();

        this.maxPlayerCount = baseGame.getMaxPlayerCount();
        this.board = baseGame.getBoard();
        this.palette = baseGame.getPalette();
        this.players = getPlayers(baseGame.getPlayerIds());
        this.scoreTracker = baseGame.getScoreTracker();
        this.state = baseGame.getState();
    }

    private List<Player> getPlayers(ArrayList<String> playerIds) {
        return playerIds.stream()
                .map(Player::getPlayer)
                .collect(Collectors.toList());
    }
}
