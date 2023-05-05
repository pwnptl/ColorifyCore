package com.colorify.colorify.model.responseBuilder;

import com.colorify.game.PlayerFacade;
import com.colorify.game.mechanics.BaseGame;
import com.colorify.game.mechanics.CellCoordinate;
import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import com.platform.core.game.GameState;
import com.platform.core.game.ScoreTracker;
import com.platform.core.player.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;


// todo: unnecessary class so far, can be removed.
@Getter
public final class GameDataResponse {

    private final String message;
    private final String gameId;
    private final int currentPlayerCount;

    private final  String currentChance;
    private final int moveCount;
    private final int maxPlayerCount;
    private final Board board;
    private final ColorifyPalette palette;
    private final ScoreTracker scoreTracker;
    private final GameState state;
    private final HashMap<String, Player> players;

    public GameDataResponse(final BaseGame baseGame, String message) {
        this.gameId = baseGame.getGameId();
        this.currentPlayerCount = baseGame.getPlayerCells().size();
        this.maxPlayerCount = baseGame.getMaxPlayerCount();
        this.board = baseGame.getBoard();
        this.palette = baseGame.getPalette();
        this.scoreTracker = baseGame.getScoreTracker();
        this.state = baseGame.getState();
        this.players = getPlayers(baseGame.getPlayerCells());
        this.currentChance = getCurrentChancePlayerId(baseGame);
        this.moveCount = baseGame.getMovesSoFar();
        this.message = message;
    }

    private String getCurrentChancePlayerId(BaseGame baseGame) {
        if(getPlayerList().size() < baseGame.getMaxPlayerCount())
            return null;
        else return getPlayerList().get(baseGame.getPlayerChanceIndex());
    }

    public ArrayList<String> getPlayerList()
    {
        ArrayList<String> players = new ArrayList<>();
        players.addAll(this.getPlayers().keySet());
        return players;
    }

    private HashMap<String, Player> getPlayers(ArrayList<CellCoordinate> playerCoordinates) {
        PlayerFacade playerFacade = new PlayerFacade();
        HashMap<String, Player> players = new HashMap<>();
        for (CellCoordinate cell : playerCoordinates) {
            String playerId = cell.getPlayerId();
            players.put(playerId, playerFacade.getPlayer(playerId));
        }
        return players;
    }
}
