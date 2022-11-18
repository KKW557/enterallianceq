package icu.kevin557.eq.utils;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author 557
 */
public class ImageUtils {

    /**
     * 缓冲图像
     * @param image 图像
     * @return 缓冲后的图像
     */
    @NotNull
    public static BufferedImage bufferedImage(@NotNull Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, null, null);
        g.dispose();
        return bufferedImage;
    }

    /**
     * 绘制文字
     * @param image 原图像
     * @param str 文字
     * @param x 左起x坐标
     * @param y 上起y坐标
     * @param font 字体
     * @param color 颜色
     */
    public static void drawString(@NotNull BufferedImage image, String str, int x, int y, Font font, Color color) {
        Graphics2D g = image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setColor(color);
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString(str, x, y);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        g.dispose();
    }

    /**
     * 绘制中心文字
     * @param image 原图像
     * @param str 文字
     * @param x 中心x坐标
     * @param y 上起y坐标
     * @param font 字体
     * @param color 颜色
     */
    public static void drawCenterString(@NotNull BufferedImage image, String str, int x, int y, Font font, Color color) {
        Graphics2D g = image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setColor(color);
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString(str, x - g.getFontMetrics().stringWidth(str) / 2, y);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        g.dispose();
    }

    /**
     * 绘制矩形
     * @param image 原图像
     * @param x 左起x坐标
     * @param y 上起y坐标
     * @param width 宽度
     * @param height 高度
     * @param color 颜色
     */
    public static void drawRect(@NotNull BufferedImage image, int x, int y, int width, int height, Color color) {
        Graphics2D g = image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.dispose();
    }

    /**
     * 填充颜色
     * @param image 原图像
     * @param color 颜色
     */
    public static void fillColor(@NotNull BufferedImage image, Color color) {
        Graphics2D g = image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setColor(color);
        g.clearRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
    }
}
