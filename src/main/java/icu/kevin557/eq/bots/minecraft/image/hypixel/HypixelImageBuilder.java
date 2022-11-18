package icu.kevin557.eq.bots.minecraft.image.hypixel;

import icu.kevin557.eq.api.image.ImageBuilder;
import icu.kevin557.eq.utils.FontUtils;
import icu.kevin557.eq.utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author 557
 */
public class HypixelImageBuilder extends ImageBuilder {

    private final String name, level, karma;

    public HypixelImageBuilder(String name, String level, String karma) {
        super(854, 480);
        this.name = name;
        this.level = level;
        this.karma = karma;
    }

    @Override
    public BufferedImage build() {
        Font font = FontUtils.getFont("Microsoft YaHei", 32F);
        ImageUtils.drawString(image, name, 200, 100, font, Color.RED);
        ImageUtils.drawString(image, level, 200, 150, font, Color.RED);
        ImageUtils.drawString(image, karma, 200, 200, font, Color.RED);
        return image;
    }
}
