package icu.kevin557.eq.api.image;

import java.awt.*;

/**
 * @author 557
 */
public class Label {

    private final String text;
    private final Color color;

    public Label(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }
}
