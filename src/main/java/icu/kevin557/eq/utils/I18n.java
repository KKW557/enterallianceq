package icu.kevin557.eq.utils;

import com.alibaba.fastjson2.JSON;
import icu.kevin557.eq.api.command.Logger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 557
 */
public class I18n {

    public static final File DIR = new File(ConfigUtils.FILES_DIR, "lang");

    /**
     * 默认语言
     */
    public static final String LANGUAGE_DEFAULT = "zh_cn";

    /**
     * 当前语言
     */
    public static String LANGUAGE;

    /**
     * 语言属性
     */
    private static Map<String, String> PROPERTIES = new HashMap<>();

    /**
     * 格式化
     * @param translateKey 翻译键
     * @return 格式化后的字符串
     */
    public static String format(String translateKey) {
        return PROPERTIES.getOrDefault(translateKey, translateKey);
    }

    /**
     * 格式化
     * @param translateKey 翻译键
     * @param parameters 格式化参数
     * @return 格式化后的字符串
     */
    public static String format(String translateKey, Object... parameters) {
        return String.format(PROPERTIES.getOrDefault(translateKey, translateKey), parameters);
    }

    /**
     * 格式化
     * @param translateKey 翻译键
     * @param mode 模式
     * @return 格式化后的字符串
     */
    public static String formatWithMode(String translateKey, String mode) {
        return PROPERTIES.getOrDefault(translateKey + "." + mode, mode);
    }

    /**
     * 读取语言配置
     */
    public static void loadLanguage() throws IOException {
        File file = new File(ConfigUtils.FILES_DIR, "language.txt");

        if (file.exists()) {
            String language = FileUtils.readFileToString(file, "UTF-8");

            if (language.isEmpty()) {
                LANGUAGE = LANGUAGE_DEFAULT;
            }
            else {
                LANGUAGE = language;
            }
        }
        else {
            LANGUAGE = LANGUAGE_DEFAULT;
        }

        Logger.info("Loaded language config.");

        loadLanguageProperties();
    }

    /**
     * 读取语言属性
     */
    public static void loadLanguageProperties() throws IOException {
        File file = new File(DIR, LANGUAGE + ".json");

        if (file.exists()) {
            PROPERTIES = JSON.parseObject(FileUtils.readFileToString(file, "UTF-8"), (Type) HashMap.class);
            Logger.info("Loaded language properties.");
        }
        else {
            Logger.info("Not found language properties!");
        }
    }

    /**
     * 保存语言配置
     */
    public static void saveLanguage() throws IOException {

        File file = new File(ConfigUtils.FILES_DIR, "language.txt");

        FileUtils.write(file, LANGUAGE, "UTF-8");
    }
}
