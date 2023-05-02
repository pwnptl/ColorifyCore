package com.colorify.game.mechanics.scoreTracker;

import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.ScoreTracker;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class ColorifyScoreTracker extends ScoreTracker {
    public ColorifyScoreTracker(){}

    public ColorifyScoreTracker(GameConfiguration gameConfiguration) {
        playerIdToScoreMap = new HashMap<>(gameConfiguration.getPlayerCount());
        totalCells = gameConfiguration.getRows() * gameConfiguration.getColumns();
    }
}