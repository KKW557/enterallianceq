package icu.kevin557.eq.utils.games.minecraft;

import org.jetbrains.annotations.Nullable;

/**
 * @author 557
 */

public enum MinecraftColor {
    /**
     * 黑色
     */
    BLACK(0, 0, '0'),
    /**
     * 深蓝色
     */
    DARK_BLUE(0x0000AA, 0x00002A, '1'),
    /**
     * 深绿色
     */
    DARK_GREEN(0x00AA00, 0x002A00, '2'),
    /**
     * 湖蓝色
     */
    DARK_AQUA(0x00AAAA, 0x002A2A, '3'),
    /**
     * 深红色
     */
    DARK_RED(0xAA0000, 0x2A0000, '4'),
    /**
     * 紫色
     */
    DARK_PURPLE(0xAA00AA, 0x2A002A, '5'),
    /**
     * 金色
     */
    GOLD(0xFFAA00, 0x402A00, '6'),
    /**
     * 灰色
     */
    GRAY(0xAAAAAA, 0x2A2A2A, '7'),
    /**
     * 深灰色
     */
    DARK_GRAY(0x555555, 0x151515, '8'),
    /**
     * 蓝色
     */
    BLUE(0x5555FF, 0x15153F, '9'),
    /**
     * 绿色
     */
    GREEN(0x55FF55, 0x153F15, 'a'),
    /**
     * 深红色
     */
    AQUA(0x55FFFF, 0x153F3F, 'b'),
    /**
     * 红色
     */
    RED(0xFF5555, 0x3F1515, 'c'),
    /**
     * 粉色
     */
    LIGHT_PURPLE(0xFF55FF, 0x3F153F, 'd'),
    /**
     * 黄色
     */
    YELLOW(0xFFFF55, 0x3F3F15, 'e'),
    /**
     * 白色
     */
    WHITE(0xFFFFFF, 0x3F3F3F, 'f'),
    /**
     * 硬币金
     */
    MINECOIN_GOLD(0xDDD605, 0x373501, 'g');


    private final java.awt.Color foreColor;
    private final java.awt.Color backColor;
    private final char code;

    MinecraftColor(int foreColor, int backColor, char code) {
        this.foreColor = new java.awt.Color(foreColor);
        this.backColor = new java.awt.Color(backColor);
        this.code = code;
    }

    @Override
    public String toString() {
        return "\u00a7" + code;
    }

    //region getters

    public java.awt.Color getForeColor() {
        return foreColor;
    }

    public java.awt.Color getBackColor() {
        return backColor;
    }

    public char getCode() {
        return code;
    }

    //endregion

    @Nullable
    public static MinecraftColor getColorByCode(char code) {
        for (MinecraftColor color : values()) {
            if (color.getCode() == code) {
                return color;
            }
        }
        return null;
    }

    public static MinecraftColor getColor(String name) {
        for (MinecraftColor color : values()) {
            if (color.name().equals(name)) {
                return color;
            }
        }
        return WHITE;
    }
}
