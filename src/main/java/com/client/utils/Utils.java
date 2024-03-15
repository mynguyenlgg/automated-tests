package com.client.utils;

public class Utils {
    private Utils() {
    }

    public static String getEnv(String name, String def) {
        String val = System.getenv(name);
        return val == null ? def : val;
    }
}
