package icu.kevin557.eq.utils.command;

import net.mamoe.mirai.utils.MiraiLogger;
import net.mamoe.mirai.utils.SimpleLogger;

/**
 * @author 557
 */
public class Logger {

    private static final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(Logger.class);

    public static void info(String message) {
        LOGGER.info(message);
    }
}
