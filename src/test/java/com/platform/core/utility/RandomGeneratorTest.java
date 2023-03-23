package com.platform.core.utility;


import org.junit.jupiter.api.Test;

class RandomGeneratorTest {

    private final RandomGenerator randomGenerator = RandomGenerator.getInstance();

    @Test
    void checkRand()
    {   // technically not a test. lol
        System.out.println(randomGenerator.getUUID());
    }
}