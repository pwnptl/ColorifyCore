package com.platform.core.game;

import com.colorify.game.utilities.GameConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class ScoreTracker {
    protected Map<String, Score> playerIdToScoreMap;
    protected int totalCells;

    public ScoreTracker(GameConfiguration gameConfiguration) {
        playerIdToScoreMap = new HashMap<>(gameConfiguration.getPlayerCount());
        totalCells = gameConfiguration.getRows() * gameConfiguration.getColumns();
    }

    public ScoreTracker() {
        playerIdToScoreMap = new HashMap<>();
        totalCells = 0;
    }

    public void updateScore(String playerId, int coveredCells) {
        Score score = playerIdToScoreMap.get(playerId);
        if (score == null) {
            score = new Score(0);
            playerIdToScoreMap.put(playerId, score);
        }
        score.setCount(coveredCells);
    }

    public float getPercentScoreByPlayer(String playerId) {
        Score score = playerIdToScoreMap.get(playerId);
        return ((float) score.getCount()) / totalCells * 100;
    }
}
