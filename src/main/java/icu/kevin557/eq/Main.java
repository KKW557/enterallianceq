package icu.kevin557.eq;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.api.command.Logger;
import icu.kevin557.eq.utils.ConfigUtils;

import java.io.IOException;

/**
 * @author 557
 */
public class Main {
    
    public static void main(String[] args) {
        init();
        EqBot.Manager.runBots();
    }

    /**
     * 初始化
     */
    private static void init() {

        /* 加载配置 */
        ConfigUtils.load();

        /* 加载机器人 */
        try {
            EqBot.Manager.loadBots();
        } catch (IOException e) {
            Logger.info("Fail to load bots!", e);
        }
    }
}
