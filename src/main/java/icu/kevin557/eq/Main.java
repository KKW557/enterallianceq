package icu.kevin557.eq;

import icu.kevin557.eq.utils.ConfigUtils;
import icu.kevin557.eq.utils.I18n;

/**
 * @author 557
 */
public class Main {

    public static void main(String[] args) {

        ConfigUtils.mkdirs();

        I18n.loadLanguages();

        EqManager.loadBots();

        EqManager.loadBotConfigs();

        EqManager.loginBots();

        EqManager.subscribeBotEvents();

        EqManager.registerBotCommands();
    }
}
