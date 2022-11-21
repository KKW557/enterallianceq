package icu.kevin557.eq.utils;

import icu.kevin557.eq.api.command.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.AttributedString;

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

        File file = new File(DIR, name + ".ttf");
        if (file.exists()) {
            try {
                return Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(file)).deriveFont(style, size);
            } catch (FontFormatException | IOException e) {
                Logger.info("Failed to load font '%s'.", file.getName());
            }
        }

        for (Font font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
            if (font.getName().equals(name)) {
                return font.deriveFont(style, size);
            }
        }

        return null;
    }

    @Nullable
    public static Font getFont(String name, float size) {
        return getFont(name, Font.PLAIN, size);
    }


    /**
     * 添加备选字体Unifont
     * @param str 字符串
     * @param font 首选字体
     * @return fallback
     */
    @NotNull
    public static AttributedString fallbackFont(String str, Font font) {
        AttributedString attributedString = new AttributedString(str);

        int strLength = str.length();
        attributedString.addAttribute(TextAttribute.FONT, font, 0, strLength);

        boolean fallback = false;
        int start = 0;
        for (int end = 0; end < strLength; end++) {
            boolean curFallback = !font.canDisplay(str.charAt(end));

            if (curFallback != fallback) {
                fallback = curFallback;

                if (fallback) {
                    start = end;
                }
                else {
                    attributedString.addAttribute(TextAttribute.FONT, FontUtils.getFont("Unifont", font.getStyle(), font.getSize()), start, end);
                }
            }
        }

        if (fallback) {
            attributedString.addAttribute(TextAttribute.FONT, FontUtils.getFont("Unifont", font.getStyle(), font.getSize()), start, strLength);
        }

        return attributedString;
    }
}
