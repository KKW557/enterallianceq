package icu.kevin557.eq.utils.games.minecraft.hypixel.bedwars;

import icu.kevin557.eq.utils.games.minecraft.MinecraftColor;
import org.jetbrains.annotations.NotNull;

/**
 * @author 557
 */
public enum BedwarsPrestige {
    /**
     * prestige
     */
    NONE(0, "✫"),
    IRON(1, "✫"),
    GOLD(2, "✫"),
    DIAMOND(3, "✫"),
    EMERALD(4, "✫"),
    SAPPHIRE(5, "✫"),
    RUBY(6, "✫"),
    CRYSTAL(7, "✫"),
    OPAL(8, "✫"),
    AMETHYST(9, "✫"),
    RAINBOW(10, "✫"),
    IRON_PRIME(11, "✪"),
    GOLD_PRIME(12, "✪"),
    DIAMOND_PRIME(13, "✪"),
    EMERALD_PRIME(14, "✪"),
    SAPPHIRE_PRIME(15, "✪"),
    RUBY_PRIME(16, "✪"),
    CRYSTAL_PRIME(17, "✪"),
    OPAL_PRIME(18, "✪"),
    AMETHYST_PRIME(19, "✪"),
    MIRROR(20, "✪"),
    LIGHT(21, "⚝"),
    DAWN(22, "⚝"),
    DUSK(23, "⚝"),
    AIR(24, "⚝"),
    WIND(25, "⚝"),
    NEBULA(26, "⚝"),
    THUNDER(27, "⚝"),
    EARTH(28, "⚝"),
    WATER(29, "⚝"),
    FIRE(30, "⚝");

    private final int id;
    private final String icon;

    BedwarsPrestige(int id, String icon) {
        this.id = id;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public static BedwarsPrestige fromId(int id) {
        for (BedwarsPrestige prestige : values()) {
            if (prestige.getId() == id) {
                return prestige;
            }
        }
        return FIRE;
    }

    public static String format(@NotNull BedwarsPrestige prestige, int level) {
        String levelTag = String.format("[%d%s]", level, prestige.getIcon());

        switch (prestige) {
            case NONE: {
                return MinecraftColor.GRAY + levelTag;
            }
            case IRON: {
                return MinecraftColor.WHITE + levelTag;
            }
            case GOLD: {
                return MinecraftColor.GOLD + levelTag;
            }
            case DIAMOND: {
                return MinecraftColor.AQUA + levelTag;
            }
            case EMERALD: {
                return MinecraftColor.DARK_GREEN + levelTag;
            }
            case SAPPHIRE: {
                return MinecraftColor.DARK_AQUA + levelTag;
            }
            case RUBY: {
                return MinecraftColor.DARK_RED + levelTag;
            }
            case CRYSTAL: {
                return MinecraftColor.LIGHT_PURPLE + levelTag;
            }
            case OPAL: {
                return MinecraftColor.BLUE + levelTag;
            }
            case AMETHYST: {
                return MinecraftColor.DARK_PURPLE + levelTag;
            }
            case RAINBOW: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.RED);
                stringBuilder.insert(3, MinecraftColor.GOLD);
                stringBuilder.insert(6, MinecraftColor.YELLOW);
                stringBuilder.insert(9, MinecraftColor.GREEN);
                stringBuilder.insert(13, MinecraftColor.AQUA);
                stringBuilder.insert(18, MinecraftColor.LIGHT_PURPLE);
                stringBuilder.insert(24, MinecraftColor.DARK_PURPLE);
                return stringBuilder.toString();
            }
            case IRON_PRIME: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GRAY);
                stringBuilder.insert(3, MinecraftColor.WHITE);
                stringBuilder.insert(9, MinecraftColor.GRAY);
                return stringBuilder.toString();
            }
            case GOLD_PRIME: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GRAY);
                stringBuilder.insert(3, MinecraftColor.YELLOW);
                stringBuilder.insert(9, MinecraftColor.GOLD);
                stringBuilder.insert(12, MinecraftColor.GRAY);
                return stringBuilder.toString();
            }
            case DIAMOND_PRIME: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GRAY);
                stringBuilder.insert(3, MinecraftColor.AQUA);
                stringBuilder.insert(9, MinecraftColor.DARK_AQUA);
                stringBuilder.insert(12, MinecraftColor.GRAY);
                return stringBuilder.toString();
            }
            case EMERALD_PRIME: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GRAY);
                stringBuilder.insert(3, MinecraftColor.GREEN);
                stringBuilder.insert(9, MinecraftColor.DARK_GREEN);
                stringBuilder.insert(12, MinecraftColor.GRAY);
                return stringBuilder.toString();
            }
            case SAPPHIRE_PRIME: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GRAY);
                stringBuilder.insert(3, MinecraftColor.DARK_AQUA);
                stringBuilder.insert(9, MinecraftColor.BLUE);
                stringBuilder.insert(12, MinecraftColor.GRAY);
                return stringBuilder.toString();
            }
            case RUBY_PRIME: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GRAY);
                stringBuilder.insert(3, MinecraftColor.RED);
                stringBuilder.insert(9, MinecraftColor.DARK_RED);
                stringBuilder.insert(12, MinecraftColor.GRAY);
                return stringBuilder.toString();
            }
            case CRYSTAL_PRIME: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GRAY);
                stringBuilder.insert(3, MinecraftColor.LIGHT_PURPLE);
                stringBuilder.insert(9, MinecraftColor.DARK_PURPLE);
                stringBuilder.insert(12, MinecraftColor.GRAY);
                return stringBuilder.toString();
            }
            case OPAL_PRIME: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GRAY);
                stringBuilder.insert(3, MinecraftColor.BLUE);
                stringBuilder.insert(9, MinecraftColor.DARK_BLUE);
                stringBuilder.insert(12, MinecraftColor.GRAY);
                return stringBuilder.toString();
            }
            case AMETHYST_PRIME: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GRAY);
                stringBuilder.insert(3, MinecraftColor.DARK_PURPLE);
                stringBuilder.insert(9, MinecraftColor.DARK_GRAY);
                stringBuilder.insert(12, MinecraftColor.GRAY);
                return stringBuilder.toString();
            }
            case MIRROR: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.DARK_GRAY);
                stringBuilder.insert(3, MinecraftColor.GRAY);
                stringBuilder.insert(6, MinecraftColor.WHITE);
                stringBuilder.insert(10, MinecraftColor.GRAY);
                stringBuilder.insert(14, MinecraftColor.DARK_GRAY);
                return stringBuilder.toString();
            }
            case LIGHT: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.WHITE);
                stringBuilder.insert(4, MinecraftColor.YELLOW);
                stringBuilder.insert(8, MinecraftColor.GOLD);
                return stringBuilder.toString();
            }
            case DAWN: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GOLD);
                stringBuilder.insert(4, MinecraftColor.WHITE);
                stringBuilder.insert(8, MinecraftColor.AQUA);
                stringBuilder.insert(11, MinecraftColor.DARK_AQUA);
                return stringBuilder.toString();
            }
            case DUSK: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.DARK_PURPLE);
                stringBuilder.insert(4, MinecraftColor.LIGHT_PURPLE);
                stringBuilder.insert(8, MinecraftColor.GOLD);
                stringBuilder.insert(11, MinecraftColor.YELLOW);
                return stringBuilder.toString();
            }
            case AIR: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.AQUA);
                stringBuilder.insert(4, MinecraftColor.WHITE);
                stringBuilder.insert(8, MinecraftColor.GRAY);
                stringBuilder.insert(12, MinecraftColor.DARK_GRAY);
                return stringBuilder.toString();
            }
            case WIND: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.WHITE);
                stringBuilder.insert(4, MinecraftColor.GREEN);
                stringBuilder.insert(8, MinecraftColor.DARK_GREEN);
                return stringBuilder.toString();
            }
            case NEBULA: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.DARK_RED);
                stringBuilder.insert(4, MinecraftColor.RED);
                stringBuilder.insert(8, MinecraftColor.LIGHT_PURPLE);
                stringBuilder.insert(12, MinecraftColor.DARK_PURPLE);
                return stringBuilder.toString();
            }
            case THUNDER: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.YELLOW);
                stringBuilder.insert(4, MinecraftColor.WHITE);
                stringBuilder.insert(8, MinecraftColor.DARK_GRAY);
                return stringBuilder.toString();
            }
            case EARTH: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.GREEN);
                stringBuilder.insert(4, MinecraftColor.DARK_GREEN);
                stringBuilder.insert(8, MinecraftColor.GOLD);
                stringBuilder.insert(12, MinecraftColor.YELLOW);
                return stringBuilder.toString();
            }
            case WATER: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.AQUA);
                stringBuilder.insert(4, MinecraftColor.DARK_AQUA);
                stringBuilder.insert(8, MinecraftColor.BLUE);
                stringBuilder.insert(12, MinecraftColor.DARK_BLUE);
                return stringBuilder.toString();
            }
            case FIRE: {
                StringBuilder stringBuilder = new StringBuilder(levelTag);
                stringBuilder.insert(0, MinecraftColor.YELLOW);
                stringBuilder.insert(4, MinecraftColor.GOLD);
                stringBuilder.insert(8, MinecraftColor.RED);
                stringBuilder.insert(12 ,MinecraftColor.DARK_RED);
                return stringBuilder.toString();
            }
            default: {
                return levelTag;
            }
        }
    }
}
