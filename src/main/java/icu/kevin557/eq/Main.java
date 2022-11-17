package icu.kevin557.eq;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.api.command.Logger;
import icu.kevin557.eq.utils.I18n;

import java.io.File;
import java.io.IOException;

/**
 * @author 557
 */
public class Main {

    // region files

    /**
     * 存放文件位置
     */
    public static final File FILES_DIR = new File("files");

    /**
     * 存放QQ文件位置
     */
    public static final File QQ_DIR = new File(FILES_DIR, "qq");

    //endregion
    
    public static void main(String[] args) {

        init();

        EqBot.Manager.runBots();
    }

    /**
     * 初始化
     * @throws IOException IO异常
     */
    private static void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            EqBot.Manager.logoutBots();
            try {
                I18n.saveLanguage();
            } catch (IOException e) {
                Logger.info("Fail to save language config!", e);
            }
        }));

        // region mkdirs

        if (!FILES_DIR.exists()) {
            FILES_DIR.mkdir();
        }
        if (!QQ_DIR.exists()) {
            QQ_DIR.mkdir();
        }
        if (!I18n.LANG_DIR.exists()) {
            I18n.LANG_DIR.mkdir();
        }

        //endregion

        // region loads

        try {
            I18n.loadLanguage();
        } catch (IOException e) {
            Logger.info("Fail to load language config!", e);
        }

        try {
            EqBot.Manager.loadBots();
        } catch (IOException e) {
            Logger.info("Fail to load bots!", e);
        }

        // endregion
    }
}
