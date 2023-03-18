package com.platform.core.utility;

import java.util.ArrayList;
import java.util.Arrays;

public class Logger {

    public static void info(String log, Object... objs) {
        ArrayList<String> strings = new ArrayList<>();
        for(Object o : objs)
        {
            strings.add(objs.toString());
        }
        System.out.println(log + " " + Arrays.toString(new ArrayList[]{strings}));
    }

    public static void test(String log) {
        System.out.println(log);
    }

    public static void error(String localizedMessage) {
        System.out.println(localizedMessage);
    }
}
