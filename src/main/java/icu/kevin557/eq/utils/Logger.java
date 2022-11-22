package icu.kevin557.eq.utils;

import net.mamoe.mirai.utils.MiraiLogger;

/**
 * @author 557
 */
public class Logger {

    private static final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(Logger.class);

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void info(String message, Throwable throwable) {
        LOGGER.info(message, throwable);
    }
}
