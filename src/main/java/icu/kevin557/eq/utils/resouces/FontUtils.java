package icu.kevin557.eq.utils.resouces;

import icu.kevin557.eq.utils.Logger;
import icu.kevin557.eq.utils.minecraft.MinecraftColor;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;

/**
 * @author 557
 */
public class FontUtils {

    @Nullable
    public static Font getFont(String name, int style, float size) {

        File file = new File(ConfigUtils.DIR_FONT, name + ".ttf");
        if (file.exists()) {
            try {
                return Font.createFont(Font.TRUETYPE_FONT, FileUtils.openInputStream(file)).deriveFont(style, size);
            } catch (FontFormatException | IOException e) {
                Logger.info("Failed to load font '" + file.getName() + "'.");
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

    @NotNull
    public static AttributedString minecraftColorString(@NotNull String str, Font font, boolean fore, int alpha) {
        String formatString = str.replaceAll("\u00a7.?", "");
        AttributedString attributedString = FontUtils.fallbackFont(formatString, font);

        int strLength = str.length();
        MinecraftColor color = MinecraftColor.WHITE;
        int codeCount = 0;
        int start = 0;

        for (int end = 0; end < strLength; end++) {

            if (str.charAt(end) == '\u00a7' && end != strLength - 1) {
                if (color != null && start != end - codeCount) {
                    attributedString.addAttribute(TextAttribute.FOREGROUND, fore ? color.getForeColor(alpha) : color.getBackColor(alpha), start, end - codeCount);
                    start = end - codeCount;
                }
                color = MinecraftColor.getColorByCode(str.charAt(end + 1));
                codeCount += 2;
                end += 1;
            }

        }

        if (color != null) {
            attributedString.addAttribute(TextAttribute.FOREGROUND, fore ? color.getForeColor(alpha) : color.getBackColor(alpha), start, formatString.length());
        }

        return attributedString;
    }

    @NotNull
    public static AttributedString minecraftColorString(@NotNull String str, Font font, boolean fore) {
        return minecraftColorString(str, font, fore, 255);
    }
}
