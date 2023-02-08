package com.colorify.game.mechanics;

import com.colorify.game.mechanics.typeAdapters.BoardCellTypeAdapter;
import com.colorify.game.mechanics.typeAdapters.ScoreTrackerTypeAdapter;
import com.platform.core.game.Cell;
import com.platform.core.game.ScoreTracker;

import java.util.HashMap;
import java.util.Map;

public class BaseFacade {
    protected Map<Class, Object> getTypeAdapters(){
        Map<Class, Object> typeAdapters = new HashMap<>();
        typeAdapters.put(Cell.class, new BoardCellTypeAdapter());
        typeAdapters.put(ScoreTracker.class, new ScoreTrackerTypeAdapter());
        return typeAdapters;
    }
}
