package com.platform.core.utility;

import com.colorify.game.utilities.Constants;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class RandomGenerator extends Random {

    private static RandomGenerator instance;

    private RandomGenerator() {
        super(Instant.now().toEpochMilli());
    }
//    private final static Random random = new Random(Instant.now().toEpochMilli());

    public static RandomGenerator getInstance() {
        if (instance == null)
            instance = new RandomGenerator();
        return instance;
    }

    public int getRandNumber(final int modulo) {
        return next(Integer.SIZE - 1) % modulo;
    }

    public String getUUID() {
        return UUID.randomUUID().toString();
    }
}
