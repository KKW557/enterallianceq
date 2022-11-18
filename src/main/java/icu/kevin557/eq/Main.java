package icu.kevin557.eq;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.utils.ConfigUtils;

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

        /* 钩子 */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            /* 登出全部机器人 */
            EqBot.Manager.logoutBots();

            /* 保存配置 */
            ConfigUtils.save();
        }));

        /* 加载配置 */
        ConfigUtils.load();
    }
}
