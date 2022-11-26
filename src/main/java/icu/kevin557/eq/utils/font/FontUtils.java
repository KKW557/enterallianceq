package icu.kevin557.eq.utils.font;

import icu.kevin557.eq.utils.Logger;
import icu.kevin557.eq.utils.games.minecraft.MinecraftColor;
import icu.kevin557.eq.utils.resouces.ConfigUtils;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import java.util.Locale;

/**
 * @author 557
 */
public class FontUtils {

    public static final Font MINECRAFT16 = FontUtils.getFont("Minecraft", 16F);
    public static final Font MINECRAFT18 = FontUtils.getFont("Minecraft", 18F);
    public static final Font MINECRAFT24 = FontUtils.getFont("Minecraft", 24F);

    public static final Font CLASHROYALE_TITLE24 = FontUtils.getFont("Supercell_clash_title_vi_th", 24F);

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
    public static AttributedString minecraftColorString(@NotNull String str, Font font, boolean fore) {

        AttributedString attributedString = FontUtils.fallbackFont(str.replaceAll("\u00a7.?", ""), font);

        MinecraftColor color = MinecraftColor.WHITE;
        int style = Font.PLAIN;
        int codeCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == 167 && i + 1 < str.length()) {

                codeCount += 2;

                int l = "0123456789abcdefklmnor".indexOf(str.toLowerCase(Locale.ENGLISH).charAt(i + 1));

                if (l < 16) {
                    color = MinecraftColor.getColorByCode(str.charAt(i + 1));
                    style = Font.PLAIN;
                }
                else if (l == 17) {
                    style = Font.BOLD;
                }
                else if (l == 18)
                {
                    style = 3;
                }
                else if (l == 19)
                {
                    style = 4;
                }
                else if (l == 20) {
                    style = Font.ITALIC;
                }
                else if (l == 21) {
                    color = MinecraftColor.WHITE;
                    style = Font.PLAIN;
                }

                i++;
            }
            else {
                if (i != 0 && str.charAt(i - 1) != 167) {
                    attributedString.addAttribute(TextAttribute.FOREGROUND, fore ? color.getForeColor() : color.getBackColor(), i - codeCount, i - codeCount + 1);

                    if (style == 3) {
                        attributedString.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON, i - codeCount, i - codeCount + 1);
                    }
                    else if (style == 4) {
                        attributedString.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, i - codeCount, i - codeCount + 1);
                    }
                    else if (style != Font.PLAIN) {
                        attributedString.addAttribute(TextAttribute.FONT, font.deriveFont(style), i - codeCount, i - codeCount + 1);
                    }
                }
                else {
                    attributedString.addAttribute(TextAttribute.FOREGROUND, fore ? color.getForeColor() : color.getBackColor(), i - codeCount, i - codeCount + 1);
                }
            }
        }

        return attributedString;
    }
}
