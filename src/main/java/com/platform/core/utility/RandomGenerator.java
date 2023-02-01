package com.platform.core.utility;

import com.colorify.game.utilities.Constants;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class RandomGenerator {

    private final static Random random = new Random(Instant.now().toEpochMilli());

    public static int getRandNumber(){
        return random.nextInt() % Constants.DEFAULT_BOARD_COLORS;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
