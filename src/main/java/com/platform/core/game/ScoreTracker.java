package com.platform.core.game;

import lombok.Getter;

import java.util.Map;

@Getter
public abstract class ScoreTracker {
    protected Map<String, Score> scores;
}
