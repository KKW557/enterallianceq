package icu.kevin557.eq.api.image;

import java.awt.image.BufferedImage;

/**
 * @author 557
 */
public abstract class ImageBuilder {

    protected final BufferedImage image;

    public ImageBuilder(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * 绘制
     * @return 绘制完成的图像
     */
    public abstract BufferedImage build();

    protected int getWidth() {
        return image.getWidth();
    }

    protected int getHeight() {
        return image.getHeight();
    }
}
