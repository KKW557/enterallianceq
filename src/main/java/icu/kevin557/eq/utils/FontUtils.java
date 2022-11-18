package icu.kevin557.eq.utils;

import icu.kevin557.eq.api.command.Logger;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author 557
 */
public class FontUtils {

    /**
     * 字体文件位置
     */
    public static final File DIR = new File(ConfigUtils.FILES_DIR, "fonts");

    @Nullable
    public static Font getFont(String name, int style, float size) {
        for (Font font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
            if (font.getName().equals(name)) {
                return font.deriveFont(style, size);
            }
        }

        File file = new File(DIR, name + ".ttf");
        if (file.exists()) {
            try {
                return Font.createFont(Font.TRUETYPE_FONT, FileUtils.openInputStream(file));
            } catch (FontFormatException | IOException e) {
                Logger.info("Failed to load font '%s'.", file.getName());
            }
        }

        return null;
    }

    @Nullable
    public static Font getFont(String name, float size) {
        return getFont(name, 0, size);
    }
}
