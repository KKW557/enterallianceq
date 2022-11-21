package icu.kevin557.eq.utils;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.api.command.Logger;
import icu.kevin557.eq.api.image.AbstractImage;
import icu.kevin557.eq.bots.minecraft.image.hypixel.HypixelImage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author 557
 */
public class ConfigUtils {

    // region files

    /**
     * 保存文件位置
     */
    public static final File FILES_DIR = new File("files");

    /**
     * 保存QQ文件位置
     */
    public static final File QQ_DIR = new File(FILES_DIR, "qq");

    //endregion

    // region keys

    public static String HYPIXEL_KEY = "";

    //endregion

    /**
     * 加载配置
     */
    public static void load() {

        // region mkdirs

        if (!FILES_DIR.exists()) {
            FILES_DIR.mkdir();
        }
        if (!FontUtils.DIR.exists()) {
            FontUtils.DIR.mkdir();
        }
        if (!I18n.DIR.exists()) {
            I18n.DIR.mkdir();
        }
        if (!QQ_DIR.exists()) {
            QQ_DIR.mkdir();
        }
        if (!AbstractImage.DIR.exists()) {
            AbstractImage.DIR.mkdir();
        }
        if (!HypixelImage.DIR.exists()) {
            HypixelImage.DIR.mkdir();
        }
        if (!HypixelImage.MAIN_DIR.exists()) {
            HypixelImage.MAIN_DIR.mkdir();
        }

        //endregion

        // region loads

        /* 加载语言配置 */
        try {
            I18n.loadLanguage();
        } catch (IOException e) {
            Logger.info("Fail to load language config!", e);
        }

        /* 加载keys */
        try {
            File file = new File(FILES_DIR, "hypixel.txt");
            if (file.exists()) {
                HYPIXEL_KEY = FileUtils.readFileToString(file, "UTF-8");
                Logger.info("Loaded hypixel key.");
            }
        }
        catch (IOException e) {
            Logger.info("Fail to load hypixel key!", e);
        }

        try {
            HypixelImage.load();
            Logger.info("Loaded hypixel images.");
        } catch (IOException e) {
            Logger.info("Fail to load hypixel images!", e);
        }

        // endregion
    }

    /**
     * 保存配置
     */
    public static void save() {
        try {
            I18n.saveLanguage();
        } catch (IOException e) {
            Logger.info("Fail to save language config!", e);
        }
    }
}
