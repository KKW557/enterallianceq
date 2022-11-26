package icu.kevin557.eq.utils.games.clashroyale;

import java.awt.image.BufferedImage;

/**
 * @author 557
 */
public enum ClashroyaleChest {

    /**
     * Chests
     */
    SILVER_CHEST("Silver Chest", null),
    GOLD_CHEST("Golden Chest", null),
    GIANT_CHEST("Giant Chest", null),
    EPIC_CHEST("Epic Chest", null),
    MAGICAL_CHEST("Magical Chest", null),
    LEGENDARY_CHEST("Legendary Chest", null),
    MEGA_LIGHTING_CHEST("Mega Lightning Chest", null),
    GOLD_CRATE("Gold Crate", null),
    PLENTIFUL_GOLD_CRATE("Plentiful Gold Crate", null),
    OVERFLOWING_GOLD_CRATE("Overflowing Gold Crate", null),
    ROYAL_WILD_CHEST("Royal Wild Chest", null);

    private final String name;
    private BufferedImage image;

    ClashroyaleChest(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public static ClashroyaleChest fromName(String name) {
        for (ClashroyaleChest chest : values()) {
            if (chest.getName().equals(name)) {
                return chest;
            }
        }
        return SILVER_CHEST;
    }
}
