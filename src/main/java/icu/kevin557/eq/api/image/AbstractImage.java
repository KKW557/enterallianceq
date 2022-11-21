package icu.kevin557.eq.api.image;

import icu.kevin557.eq.utils.ConfigUtils;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author 557
 */
public abstract class AbstractImage {

    public static final File DIR = new File(ConfigUtils.FILES_DIR, "images");

    protected final BufferedImage image;

    public AbstractImage(int width, int height) {
        this(new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB));
    }

    public AbstractImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * 绘制
     * @return 绘制完成的图像
     */
    public abstract BufferedImage get();

    protected int getWidth() {
        return image.getWidth();
    }

    protected int getHeight() {
        return image.getHeight();
    }
}
