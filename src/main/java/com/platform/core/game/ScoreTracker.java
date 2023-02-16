package com.platform.core.game;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class ScoreTracker {
    protected Map<String, Score> scores;

    public void addScore(String playerId, Score score){
        scores.put(playerId,score);
    };
}
