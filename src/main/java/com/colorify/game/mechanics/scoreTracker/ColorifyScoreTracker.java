package com.colorify.game.mechanics.scoreTracker;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.ScoreTracker;
import lombok.Getter;

@Getter
public class ColorifyScoreTracker extends ScoreTracker {
    public ColorifyScoreTracker() {
        super();
    }

    public ColorifyScoreTracker(GameConfiguration gameConfiguration) {
        super(gameConfiguration);
    }
}