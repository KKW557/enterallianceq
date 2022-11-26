package icu.kevin557.eq.image;

import icu.kevin557.eq.EnterallianceQ;

import java.awt.image.BufferedImage;

/**
 * @author 557
 */
public abstract class AbstractImage {

    protected final BufferedImage image;

    protected final EnterallianceQ bot;

    public AbstractImage(EnterallianceQ bot, int width, int height) {
        this(bot, new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
    }

    public AbstractImage(EnterallianceQ bot, BufferedImage image) {
        this.bot = bot;
        this.image = image;
    }

    public abstract BufferedImage get();

    protected int getWidth() {
        return image.getWidth();
    }

    protected int getHeight() {
        return image.getHeight();
    }
}
