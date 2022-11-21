package icu.kevin557.eq.utils.minecraft;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * @author 557
 */
public class HypixelUtils {

    public static final String PLAYER_URL = "https://api.hypixel.net/player?key=%s&uuid=%s";
    public static final String FRIEND_URL = "https://api.hypixel.net/friends?key=%s&uuid=%s";
    public static final String GUILD_URL = "https://api.hypixel.net/guild?key=%s&player=%s";
    public static final String STATUS_URL = "https://api.hypixel.net/status?key=%s&uuid=%s";

    public static double formatNetworkLevel(double exp) {
        return Math.sqrt(0.0008 * new BigDecimal(exp).floatValue() + 12.25) - 2.5;
    }

    public enum Rank {
        /**
         * VIP
         */
        VIP("VIP", MinecraftUtils.Color.GREEN + "[VIP]"),
        /**
         * VIP+
         */
        VIP_PLUS("VIP_PLUS", MinecraftUtils.Color.GREEN + "[VIP" + MinecraftUtils.Color.GOLD + "+" + MinecraftUtils.Color.GREEN + "]"),
        /**
         * MVP
         */
        MVP("MVP", MinecraftUtils.Color.AQUA + "[MVP]"),
        /**
         * MVP+
         */
        MVP_PLUS("MVP_PLUS", MinecraftUtils.Color.AQUA + "[MVP" + "%s+" + MinecraftUtils.Color.AQUA + "]"),
        /**
         * MVP++
         */
        SUPERSTAR("SUPERSTAR", "%1$s[MVP%2$s++%1$s]"),
        /**
         * GM
         */
        GAME_MASTER("GAME_MASTER", MinecraftUtils.Color.DARK_GREEN + "[GM]"),
        /**
         * ADMIN
         */
        ADMIN("ADMIN", MinecraftUtils.Color.RED + "[ADMIN]"),
        /**
         * YT
         */
        YT("YOUTUBER", MinecraftUtils.Color.RED + "[" + MinecraftUtils.Color.WHITE + "YT" + MinecraftUtils.Color.RED + "]"),
        /**
         * NORMAL
         */
        NORMAL("NORMAL", "");

        /**
         * Rank 名称
         */
        private final String name;

        /**
         * Rank b标签
         */
        private final String tag;

        Rank(String name, String tag) {
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

            for (Rank rank : values()) {
                if (rank.getName().equals(name)) {
                    return String.format(rank.getTag(), color);
                }
            }

            return name;
        }
    }
}
