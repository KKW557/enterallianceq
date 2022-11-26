package icu.kevin557.eq.utils.render;

import icu.kevin557.eq.utils.font.FontUtils;
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

    public static final Color WATERMARK_COLOR = new Color(255, 255, 255, 77);

    @NotNull
    public static BufferedImage bufferedImage(@NotNull Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, null, null);
        g.dispose();
        return bufferedImage;
    }

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

    public static void drawImage(@NotNull BufferedImage image1, BufferedImage image2, int x, int y) {
        Graphics2D g = image1.createGraphics();
        g.drawImage(image2, x, y, null);
        g.dispose();
    }

    public static void drawString(@NotNull BufferedImage image, @NotNull AttributedString attributedString, float x, float y) {
        Graphics2D g = image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString(attributedString.getIterator(), x, y);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        g.dispose();
    }

    public static void drawCenterString(@NotNull BufferedImage image, @NotNull AttributedString attributedString, float x, float y) {
        Graphics2D g = image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AttributedCharacterIterator attributedCharacterIterator = attributedString.getIterator();
        TextLayout textLayout = new TextLayout(attributedCharacterIterator, g.getFontRenderContext());
        g.drawString(attributedCharacterIterator, (float) (x - textLayout.getBounds().getWidth() / 2F), y);
        g.dispose();
    }

    public static void drawString(@NotNull BufferedImage image, String str, float x, float y, Font font, Color color) {
        AttributedString attributedString = FontUtils.fallbackFont(str, font);
        attributedString.addAttribute(TextAttribute.FOREGROUND, color);
        drawString(image, attributedString, x, y);
    }

    public static void drawCenterString(@NotNull BufferedImage image, String str, float x, float y, Font font, Color color) {
        AttributedString attributedString = FontUtils.fallbackFont(str, font);
        attributedString.addAttribute(TextAttribute.FOREGROUND, color);
        drawCenterString(image, attributedString, x, y);
    }

    public static void drawRect(@NotNull BufferedImage image, int x, int y, int width, int height, Color color) {
        Graphics2D g = image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.dispose();
    }

    public static void fillColor(@NotNull BufferedImage image, Color color) {
        Graphics2D g = image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setColor(color);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
    }

    public static void waterMark(BufferedImage image, float x, float y, float size) {
        ImageUtils.drawString(image, "EnterallianceQ", x, y, FontUtils.getFont("Microsoft YaHei UI", Font.BOLD, size), WATERMARK_COLOR);
    }
}
