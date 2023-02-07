package com.colorify.game.mechanics.scoreTracker;

import com.platform.core.game.ScoreTracker;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class ColorifyScoreTracker extends ScoreTracker {
    public ColorifyScoreTracker(){}
    public ColorifyScoreTracker(final int maxPlayerCount) {
        scores = new HashMap<>(maxPlayerCount);
    }
}