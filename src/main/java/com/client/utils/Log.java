package com.client.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Log {
    private static Logger loggerInstance;

    private static Logger getLogger() {
        if (Objects.isNull(loggerInstance)) {
            loggerInstance = LogManager.getLogger(Logger.class);
        }
        return loggerInstance;
    }

    public static void postStep(String message) {
        getLogger().info("POST-CONDITION: " + message);
    }

    public static void preStep(String message) {
        getLogger().info("PRE-CONDITION: " + message);
    }

    public static void step(String message) {
        getLogger().info("STEP: " + message);
    }

    public static void verify(String message) {
        getLogger().info("VERIFY-POINT: " + message);
    }

    public static void debug(String message) {
        getLogger().debug(message);
    }
}
