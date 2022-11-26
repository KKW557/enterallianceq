package icu.kevin557.eq.utils.games.clashroyale;

import icu.kevin557.eq.utils.resouces.ConfigUtils;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author 557
 */
public class ClashroyaleUtils {

    public static final String PLAYER_URL = "https://api.clashroyale.com/v1/players/%s";
    public static final String CHEST_URL = "https://api.clashroyale.com/v1/players/%s/upcomingchests";

    public static final Pattern TAG_PATTERN = Pattern.compile("#?([A-Z0-9]+)");

    public static boolean isTag(String tag) {
        return TAG_PATTERN.matcher(tag).matches();
    }

    @NotNull
    public static String transTag(@NotNull String tag) {
        return (tag.startsWith("#") ? tag.replace("#", "%23") : "%23" + tag);
    }

    public static void loadImages() {

        File file = new File(ConfigUtils.DIR_IMAGE, "clashroyale");

        if (file.exists()) {
            for (ClashroyaleChest chest : ClashroyaleChest.values()) {
                File imageFile = new File(file, chest.getName() + ".png");
                if (file.exists()) {
                    try {
                        chest.setImage(ImageIO.read(imageFile));
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }
}
