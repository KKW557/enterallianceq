package icu.kevin557.eq.utils.render;

import icu.kevin557.eq.utils.games.minecraft.MinecraftUtils;
import icu.kevin557.eq.utils.font.FontUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author 557
 */
public class MinecraftRenderer {

    public static void drawPlayerModel(BufferedImage image, String id, String width, String height, String time, String theta, String phi, int x, int y) {
        BufferedImage playerModel = MinecraftUtils.playerModel(id, width, height, null, null, null);
        if (playerModel != null) {
            ImageUtils.drawImage(image, playerModel, x, y);
        }
    }

    public static void drawCenterStringWithShadow(BufferedImage image, String str, Font font, float x, float y) {
        ImageUtils.drawCenterString(image, FontUtils.minecraftColorString(str, font, false), x + 1, y + 1);
        ImageUtils.drawCenterString(image, FontUtils.minecraftColorString(str, font, true), x - 1, y - 1);
    }

    public static void drawStringWithShadow(BufferedImage image, String str, Font font, float x, float y) {
        ImageUtils.drawString(image, FontUtils.minecraftColorString(str, font, false), x + 1, y + 1);
        ImageUtils.drawString(image, FontUtils.minecraftColorString(str, font, true), x - 1, y - 1);
    }
}
