package com.colorify.colorify.model.responseBuilder;

import com.colorify.game.PlayerFacade;
import com.colorify.game.mechanics.BaseGame;
import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import com.platform.core.game.GameState;
import com.platform.core.game.ScoreTracker;
import com.platform.core.player.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


// todo: unnecessary class so far, can be removed.
@Getter
public final class GameDataResponse {

    private final String message;
    private final String gameId;
    private final int currentPlayerCount;

    private final String currentChance;
    private final int moveCount;
    private final int maxPlayerCount;
    private final Board board;
    private final ColorifyPalette palette;
    private final ScoreTracker scoreTracker;
    private final GameState state;
    private final LinkedHashMap<String, Player> players; // preserve order

    public GameDataResponse(final BaseGame baseGame, String message) {
        this.gameId = baseGame.getGameId();
        this.currentPlayerCount = baseGame.getPlayerCells().size();
        this.maxPlayerCount = baseGame.getMaxPlayerCount();
        this.board = baseGame.getBoard();
        this.palette = baseGame.getPalette();
        this.scoreTracker = baseGame.getScoreTracker();
        this.state = baseGame.getState();
        this.players = getPlayers(baseGame.getPlayerIds());
        this.currentChance = baseGame.getPlayerChance();
        this.moveCount = baseGame.getMovesSoFar();
        this.message = message;
    }

    public ArrayList<String> getPlayerList()
    {
        return new ArrayList<>(this.players.keySet());
    }

    private LinkedHashMap<String, Player> getPlayers(List<String> playerIds) {
        PlayerFacade playerFacade = new PlayerFacade();
        LinkedHashMap<String, Player> players = new LinkedHashMap<>();
        for (String playerId : playerIds) {
            players.put(playerId, playerFacade.getPlayer(playerId));
        }
        return players;
    }
}
