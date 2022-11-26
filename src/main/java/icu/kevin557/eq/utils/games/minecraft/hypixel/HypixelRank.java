package icu.kevin557.eq.utils.games.minecraft.hypixel;

import icu.kevin557.eq.utils.games.minecraft.MinecraftColor;

/**
 * @author 557
 */
public enum HypixelRank {
    /**
     * VIP
     */
    VIP("VIP", MinecraftColor.GREEN + "[VIP]"),
    /**
     * VIP+
     */
    VIP_PLUS("VIP_PLUS", MinecraftColor.GREEN + "[VIP" + MinecraftColor.GOLD + "+" + MinecraftColor.GREEN + "]"),
    /**
     * MVP
     */
    MVP("MVP", MinecraftColor.AQUA + "[MVP]"),
    /**
     * MVP+
     */
    MVP_PLUS("MVP_PLUS", MinecraftColor.AQUA + "[MVP" + "%s+" + MinecraftColor.AQUA + "]"),
    /**
     * MVP++
     */
    SUPERSTAR("SUPERSTAR", "%1$s[MVP%2$s++%1$s]"),
    /**
     * GM
     */
    GAME_MASTER("GAME_MASTER", MinecraftColor.DARK_GREEN + "[GM]"),
    /**
     * ADMIN
     */
    ADMIN("ADMIN", MinecraftColor.RED + "[ADMIN]"),
    /**
     * YT
     */
    YT("YOUTUBER", MinecraftColor.RED + "[" + MinecraftColor.WHITE + "YT" + MinecraftColor.RED + "]");

    /**
     * Rank 名称
     */
    private final String name;

    /**
     * Rank b标签
     */
    private final String tag;

    HypixelRank(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    // region getters

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    // endregion

    public static String getRank(String name, Object... color) {

        for (HypixelRank rank : values()) {
            if (rank.getName().equals(name)) {
                return String.format(rank.getTag(), color);
            }
        }
        return name;
    }
}
