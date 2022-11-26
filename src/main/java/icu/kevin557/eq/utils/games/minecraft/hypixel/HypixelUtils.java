package icu.kevin557.eq.utils.games.minecraft.hypixel;

import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.utils.games.minecraft.MinecraftColor;
import icu.kevin557.eq.utils.resouces.ConfigUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 557
 */
public class HypixelUtils {

    public static final List<BufferedImage> MAIN_IMAGES = new ArrayList<>();
    public static final List<BufferedImage> BEDWARS_IMAGES = new ArrayList<>();
    public static final List<BufferedImage> SKYWARS_IMAGES = new ArrayList<>();

    public static final String PLAYER_URL = "https://api.hypixel.net/player?key=%s&uuid=%s";
    public static final String FRIEND_URL = "https://api.hypixel.net/friends?key=%s&uuid=%s";
    public static final String GUILD_URL = "https://api.hypixel.net/guild?key=%s&player=%s";
    public static final String STATUS_URL = "https://api.hypixel.net/status?key=%s&uuid=%s";
    public static final String PUNISHMENTS_URL = "https://api.hypixel.net/punishmentstats?key=%s";

    public static double formatNetworkLevel(double exp) {
        return Math.sqrt(0.0008 * new BigDecimal(exp).floatValue() + 12.25) - 2.5;
    }

    @Nullable
    public static String getRank(@NotNull JSONObject player) {
        if (player.getString("prefix") != null && !"NONE".equals(player.getString("prefix"))) {
            return player.getString("prefix");
        }
        if (player.getString("rank") != null && !"NONE".equals(player.getString("rank")) && !"NORMAL".equals(player.getString("rank"))) {
            return HypixelRank.getRank(player.getString("rank"));
        }
        String plusColor = player.getString("rankPlusColor") == null ? MinecraftColor.RED.toString() : MinecraftColor.getColor(player.getString("rankPlusColor")).toString();
        if (player.getString("monthlyPackageRank") != null && !"NONE".equals(player.getString("monthlyPackageRank"))) {
            String rankColor = player.get("monthlyRankColor") == null ? MinecraftColor.GOLD.toString() : MinecraftColor.getColor(player.getString("monthlyRankColor")).toString();
            return HypixelRank.getRank(player.getString("monthlyPackageRank"), rankColor, plusColor);
        }
        if (player.getString("newPackageRank") != null && !"NONE".equals(player.getString("newPackageRank"))) {
            return HypixelRank.getRank(player.getString("newPackageRank"), plusColor);
        }
        if (player.getString("packageRank") != null && !"NONE".equals(player.getString("packageRank"))) {
            return HypixelRank.getRank(player.getString("packageRank"), plusColor);
        }
        return null;
    }

    public static void loadImages() {
        MAIN_IMAGES.clear();
        File file = new File(ConfigUtils.DIR_IMAGE, "hypixel");

        if (file.exists()) {

            File file1 = new File(file, "main");
            if (file1.exists()) {
                MAIN_IMAGES.clear();
                for (File f : Objects.requireNonNull(file1.listFiles())) {
                    try {
                        MAIN_IMAGES.add(ImageIO.read(f));
                    }
                    catch (IOException ignored) {
                    }
                }
            }

            File file2 = new File(file, "bedwars");
            if (file2.exists()) {
                BEDWARS_IMAGES.clear();
                for (File f : Objects.requireNonNull(file2.listFiles())) {
                    try {
                        BEDWARS_IMAGES.add(ImageIO.read(f));
                    }
                    catch (IOException ignored) {
                    }
                }
            }

            File file3 = new File(file, "skywars");
            if (file3.exists()) {
                SKYWARS_IMAGES.clear();
                for (File f : Objects.requireNonNull(file3.listFiles())) {
                    try {
                        SKYWARS_IMAGES.add(ImageIO.read(f));
                    }
                    catch (IOException ignored) {
                    }
                }
            }
        }
    }
}
