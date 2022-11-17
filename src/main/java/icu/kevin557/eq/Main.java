package icu.kevin557.eq;

import icu.kevin557.eq.utils.bot.EqBot;

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
        FILES_DIR.mkdir();
        QQ_DIR.mkdir();

        Runtime.getRuntime().addShutdownHook(new Thread(EqBot.Manager::logoutBots));

        try {
            EqBot.Manager.loadBots();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EqBot.Manager.runBots();
    }
}
