package com.platform.core.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logger {
    static List<String> logsToShow = new ArrayList<>();
    static List<String> logsNotToShow = new ArrayList<>();

    static {
        logsNotToShow.add("com.platform.core.database.MongoDB");
        logsNotToShow.add("com.colorify.colorify.PostBeanInit");
        logsNotToShow.add("com.colorify.colorify.controller.WebController");
        logsNotToShow.add("com.colorify.colorify.controller.GameController");
        logsNotToShow.add("com.colorify.colorify.controller.PlayerController");

    }

    public static void info(String log, Object... objs) {
        ArrayList<String> strings = new ArrayList<>();
        if (objs != null)
            for (Object o : objs) {
                strings.add(Arrays.toString(objs));
            }
        System.out.println(log + " " + Arrays.toString(new ArrayList[]{strings}));
    }

    public static void info(String log, String str) {
        if (logsNotToShow.contains(log))
            return;
        else if (logsToShow.contains(log)) {
            System.out.print(log + " : ");
            info(str);
        } else {
            System.out.print(log + " : ");
            info(str);
        }
    }

    public static void test(String log) {
        System.out.println(log);
    }

    public static void error(String localizedMessage) {
        System.out.println(localizedMessage);
    }

    public static void error(String log, String localizedMessage) {
        System.out.print(log + " : ");
        error(localizedMessage);
    }

    public static void debug(String message) {
        System.out.println(message);
    }
}
