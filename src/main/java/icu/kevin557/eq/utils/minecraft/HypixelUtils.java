package icu.kevin557.eq.utils.minecraft;

import icu.kevin557.eq.utils.resouces.ConfigUtils;

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

    public static final String PLAYER_URL = "https://api.hypixel.net/player?key=%s&uuid=%s";
    public static final String FRIEND_URL = "https://api.hypixel.net/friends?key=%s&uuid=%s";
    public static final String GUILD_URL = "https://api.hypixel.net/guild?key=%s&player=%s";
    public static final String STATUS_URL = "https://api.hypixel.net/status?key=%s&uuid=%s";

    public static double formatNetworkLevel(double exp) {
        return Math.sqrt(0.0008 * new BigDecimal(exp).floatValue() + 12.25) - 2.5;
    }

    public static void loadImages() {
        MAIN_IMAGES.clear();
        File file = new File(ConfigUtils.DIR_IMAGE, "hypixel");

        if (file.exists()) {

            File file1 = new File(file, "main");
            if (file1.exists()) {
                for (File f : Objects.requireNonNull(file1.listFiles())) {
                    try {
                        MAIN_IMAGES.add(ImageIO.read(f));
                    }
                    catch (IOException ignored) {
                    }
                }
            }

        }
    }
}
