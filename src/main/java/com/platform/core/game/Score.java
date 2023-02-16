package com.platform.core.game;

import lombok.Getter;

@Getter
public class Score {

    private int count;

    public Score(int count) {
        this.count = count;
    }
}
