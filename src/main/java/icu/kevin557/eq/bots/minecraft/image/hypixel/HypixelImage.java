package icu.kevin557.eq.bots.minecraft.image.hypixel;

import icu.kevin557.eq.api.image.AbstractImage;
import icu.kevin557.eq.utils.FontUtils;
import icu.kevin557.eq.utils.I18n;
import icu.kevin557.eq.utils.ImageUtils;
import icu.kevin557.eq.utils.RandomUtils;
import icu.kevin557.eq.utils.minecraft.MinecraftUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @author 557
 */
public class HypixelImage extends AbstractImage {

    public static final File DIR = new File(AbstractImage.DIR, "hypixel");
    public static final File MAIN_DIR = new File(DIR, "main");

    private static final List<BufferedImage> MAIN_IMAGES = new ArrayList<>();

    private static final Font FONT18 = FontUtils.getFont("Minecraft", 18F);
    private static final Font FONT16 = FontUtils.getFont("Minecraft", 16F);

    // region info

    private final String id;
    private final String name;
    private final String karma;
    private final String level;
    private final String language;
    private final String achievementPoints;
    private final String guild;
    private final String friend;
    private final String channel;
    private final String firstLogin;
    private final boolean status;
    private final String lastLogin;
    private final String gameType;
    private final String mode;
    private final String map;
    private final String lastLogout;
    private final String recent;
    private final String ranksGifted;

    // endregion

    public HypixelImage(String id, String name, String karma, String level, String language, String achievementPoints, String guild, String friend, String channel, String firstLogin, boolean status, String lastLogin, String gameType, String mode, String map, String lastLogout, String recent, String ranksGifted) {
        super(ImageUtils.bufferedImage(Objects.requireNonNull(RandomUtils.random(MAIN_IMAGES))));
        this.id = id;
        this.name = name;
        this.karma = karma;
        this.level = level;
        this.language = language;
        this.achievementPoints = achievementPoints;
        this.guild = guild;
        this.friend = friend;
        this.channel = channel;
        this.firstLogin = firstLogin;
        this.status = status;
        this.lastLogin = lastLogin;
        this.gameType = gameType;
        this.mode = mode;
        this.map = map;
        this.lastLogout = lastLogout;
        this.recent = recent;
        this.ranksGifted = ranksGifted;
    }

    @Override
    public BufferedImage get() {

        BufferedImage playerModel = MinecraftUtils.playerModel(id, 128, 256);
        if (playerModel != null) {
            ImageUtils.drawImage(image, playerModel, 116, 86);
        }

        if (ranksGifted == null) {
            this.drawCenterStringWithShadow(name, FONT18, 180, 70);
        }
        else {
            this.drawCenterStringWithShadow(name, FONT18, 180, 62);
            this.drawCenterStringWithShadow(MinecraftUtils.Color.YELLOW + I18n.format("chat.hypixel.ranksGifted") + MinecraftUtils.Color.AQUA + ranksGifted, FONT16, 180, 90);
        }

        this.drawStringWithShadow(MinecraftUtils.Color.LIGHT_PURPLE + I18n.format("chat.hypixel.karma") + MinecraftUtils.Color.WHITE + karma, FONT18, 409, 84);
        this.drawStringWithShadow(MinecraftUtils.Color.RED + I18n.format("chat.hypixel.level") + MinecraftUtils.Color.WHITE + level, FONT18, 409, 118);
        this.drawStringWithShadow(MinecraftUtils.Color.GOLD + I18n.format("chat.hypixel.language") + MinecraftUtils.Color.WHITE + language, FONT18, 409, 152);
        this.drawStringWithShadow(MinecraftUtils.Color.YELLOW + I18n.format("chat.hypixel.achievement") + MinecraftUtils.Color.WHITE + achievementPoints, FONT18, 409, 186);
        this.drawStringWithShadow(MinecraftUtils.Color.GREEN + I18n.format("chat.hypixel.guild") + MinecraftUtils.Color.WHITE + guild, FONT18, 409, 220);
        this.drawStringWithShadow(MinecraftUtils.Color.AQUA + I18n.format("chat.hypixel.friend") + MinecraftUtils.Color.WHITE + friend, FONT18, 409, 254);
        this.drawStringWithShadow(MinecraftUtils.Color.BLUE + I18n.format("chat.hypixel.channel") + MinecraftUtils.Color.WHITE + channel, FONT18, 409, 288);
        this.drawStringWithShadow(MinecraftUtils.Color.DARK_PURPLE + I18n.format("chat.hypixel.join") + MinecraftUtils.Color.WHITE + firstLogin, FONT18, 409, 322);

        if (lastLogin == null) {
            this.drawCenterStringWithShadow(MinecraftUtils.Color.DARK_RED + I18n.format("chat.hypixel.forbidden"), FONT18, 637, 407);
        }
        else {
            if (status) {
                this.drawStringWithShadow(I18n.format("chat.hypixel.status") + MinecraftUtils.Color.GREEN + I18n.format("chat.hypixel.online") + "  " + MinecraftUtils.Color.DARK_GRAY + lastLogin, FONT18, 409, 390);

                String session = "";

                if (gameType != null) {
                    session += I18n.format("chat.hypixel.gameType." + gameType);
                }
                if (mode != null) {
                    session += I18n.format("chat.hypixel.mode." + mode);
                }
                if (map != null) {
                    session += " " + map;
                }

                this.drawStringWithShadow(I18n.format("chat.hypixel.at") + MinecraftUtils.Color.GRAY + session, FONT18, 409, 424);
            }
            else {
                this.drawStringWithShadow(I18n.format("chat.hypixel.status") + MinecraftUtils.Color.RED + I18n.format("chat.hypixel.offline") + (lastLogout == null ? "" : ("  " + MinecraftUtils.Color.DARK_GRAY + lastLogout)), FONT18, 409, 390);
                this.drawStringWithShadow(I18n.format("chat.hypixel.recent") + MinecraftUtils.Color.GRAY + I18n.format("chat.hypixel.gameType." + recent), FONT18, 409, 424);
            }
        }
        return image;
    }

    private void drawCenterStringWithShadow(String str, Font FONT18, float x, float y) {
        ImageUtils.drawCenterString(image, MinecraftUtils.colorString(str, FONT18, false), x + 1, y + 1);
        ImageUtils.drawCenterString(image, MinecraftUtils.colorString(str, FONT18, true), x - 1, y - 1);
    }

    private void drawStringWithShadow(String str, Font FONT18, float x, float y) {
        ImageUtils.drawString(image, MinecraftUtils.colorString(str, FONT18, false), x + 1, y + 1);
        ImageUtils.drawString(image, MinecraftUtils.colorString(str, FONT18, true), x - 1, y - 1);
    }

    public static void load() throws IOException {
        MAIN_IMAGES.clear();
        for (File file : Objects.requireNonNull(MAIN_DIR.listFiles())) {
            MAIN_IMAGES.add(ImageIO.read(file));
        }
    }
}
