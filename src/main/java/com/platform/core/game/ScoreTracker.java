package com.platform.core.game;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class ScoreTracker {
    protected Map<String, Score> playerIdToScoreMap;
    protected int totalCells;

    public void addScore(String playerId, Score score) {
        playerIdToScoreMap.put(playerId, score);
    }

    public float getPercentScoreByPlayer(String playerId) {
        Score score = playerIdToScoreMap.get(playerId);
        return ((float) score.getCount()) / totalCells * 100;
    }
}
