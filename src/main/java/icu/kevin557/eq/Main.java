package icu.kevin557.eq;

import icu.kevin557.eq.utils.minecraft.HypixelUtils;
import icu.kevin557.eq.utils.resouces.ConfigUtils;
import icu.kevin557.eq.utils.resouces.I18n;

/**
 * @author 557
 */
public class Main {

    public static void main(String[] args) {

        ConfigUtils.mkdirs();

        I18n.loadLanguages();

        HypixelUtils.loadImages();

        EqManager.loadBots();

        EqManager.loadBotConfigs();

        EqManager.loginBots();

        EqManager.subscribeBotEvents();

        EqManager.registerBotCommands();
    }
}
