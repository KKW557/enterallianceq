package icu.kevin557.eq.utils;

import icu.kevin557.eq.utils.resouces.FontUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

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
     * 缩放图像
     * @param image 图像
     * @param scale 比例
     * @return 缩放后的图像
     */
    @NotNull
    public static BufferedImage scaleImage(@NotNull BufferedImage image, float scale) {
        int width = (int) (image.getWidth() * scale);
        int height = (int) (image.getHeight() * scale);
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaledImage.createGraphics();
        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_ANTIALIAS_ON);
        renderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHints(renderingHints);
        g.drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(), image.getWidth(), null);
        g.dispose();
        return scaledImage;
    }

    /**
     * 将 image2 绘制到 image1 上
     * @param image1 背景图
     * @param image2 图
     * @param x x
     * @param y y
     */
    public static void drawImage(@NotNull BufferedImage image1, BufferedImage image2, int x, int y) {
        Graphics2D g = image1.createGraphics();
        g.drawImage(image2, x, y, null);
        g.dispose();
    }


    /**
     * 绘制带属性的文字
     * @param image 原图像
     * @param attributedString 带属性的文字
     * @param x 左起x坐标
     * @param y 上起y坐标
     */
    public static void drawString(@NotNull BufferedImage image, AttributedString attributedString, float x, float y) {
        Graphics2D g = image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString(attributedString.getIterator(), x, y);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        g.dispose();
    }


    /**
     * 绘制中心带属性的文字
     * @param image 原图像
     * @param attributedString 带属性的文字
     * @param x 中心x坐标
     * @param y 上起y坐标
     */
    public static void drawCenterString(@NotNull BufferedImage image, AttributedString attributedString, float x, float y) {
        Graphics2D g = image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AttributedCharacterIterator attributedCharacterIterator = attributedString.getIterator();
        TextLayout textLayout = new TextLayout(attributedCharacterIterator, g.getFontRenderContext());
        g.drawString(attributedCharacterIterator, (float) (x - textLayout.getBounds().getWidth() / 2F), y);
        g.dispose();
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
    public static void drawString(@NotNull BufferedImage image, String str, float x, float y, Font font, Color color) {
        AttributedString attributedString = FontUtils.fallbackFont(str, font);
        attributedString.addAttribute(TextAttribute.FOREGROUND, color);
        drawString(image, attributedString, x, y);
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
    public static void drawCenterString(@NotNull BufferedImage image, String str, float x, float y, Font font, Color color) {
        AttributedString attributedString = FontUtils.fallbackFont(str, font);
        attributedString.addAttribute(TextAttribute.FOREGROUND, color);
        drawCenterString(image, attributedString, x, y);
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
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
    }
}
