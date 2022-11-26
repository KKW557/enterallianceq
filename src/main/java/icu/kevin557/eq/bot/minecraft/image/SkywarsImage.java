package icu.kevin557.eq.bot.minecraft.image;

import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.image.AbstractImage;
import icu.kevin557.eq.utils.font.FontUtils;
import icu.kevin557.eq.utils.games.minecraft.MinecraftColor;
import icu.kevin557.eq.utils.games.minecraft.hypixel.HypixelUtils;
import icu.kevin557.eq.utils.math.RandomUtils;
import icu.kevin557.eq.utils.render.ImageUtils;
import icu.kevin557.eq.utils.render.MinecraftRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @author 557
 */
public class SkywarsImage extends AbstractImage {

    // region info

    private final String id;
    private final String name;

    private final String level;

    // endregion

    public SkywarsImage(EnterallianceQ bot, String id, String name, String level) {
        super(bot, ImageUtils.bufferedImage(Objects.requireNonNull(RandomUtils.random(HypixelUtils.SKYWARS_IMAGES))));
        this.id = id;
        this.name = name;
        this.level = level;
    }

    @Override
    public BufferedImage get() {
        MinecraftRenderer.drawPlayerModel(image, id, "128", "256", null, null, null, 116, 90);

        MinecraftRenderer.drawCenterStringWithShadow(image, name, FontUtils.MINECRAFT18, 180, 76);

        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.skywars.level") + level, FontUtils.MINECRAFT18, 114, 424);

        return image;
    }
}
