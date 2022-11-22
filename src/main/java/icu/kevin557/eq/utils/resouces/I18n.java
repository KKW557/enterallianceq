package icu.kevin557.eq.utils.resouces;

import com.alibaba.fastjson2.JSON;
import icu.kevin557.eq.utils.Logger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Map;
import java.util.Objects;

/**
 * @author 557
 */
public class I18n {

    private static final Map<String, Map<String, String>> PROPERTIES_MAP = new HashMap<>();

    public static void loadLanguages() {
        PROPERTIES_MAP.clear();
        for (File file : Objects.requireNonNull(ConfigUtils.DIR_LANG.listFiles())) {
            try {
                PROPERTIES_MAP.put(file.getName().replaceAll("\\.json", ""), JSON.parseObject(FileUtils.readFileToString(file, "UTF-8"), (Type) HashMap.class));
            } catch (IOException e) {
                Logger.info(String.format("Failed to load language '%s'.", file.getName()));
            }
        }
    }

    public static String format(String language, String translateKey, Object... params) {
        String s = PROPERTIES_MAP.getOrDefault(language, defaultProperties()).getOrDefault(translateKey, translateKey);

        try {
            return String.format(s, params);
        }
        catch (IllegalFormatException e) {
            return s;
        }
    }

    private static Map<String, String> defaultProperties() {
        return PROPERTIES_MAP.get("zh_cn");
    }
}
