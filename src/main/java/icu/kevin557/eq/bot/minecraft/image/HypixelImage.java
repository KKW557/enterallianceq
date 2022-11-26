package icu.kevin557.eq.bot.minecraft.image;

import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.image.AbstractImage;
import icu.kevin557.eq.utils.render.ImageUtils;
import icu.kevin557.eq.utils.math.RandomUtils;
import icu.kevin557.eq.utils.games.minecraft.hypixel.HypixelUtils;
import icu.kevin557.eq.utils.games.minecraft.MinecraftColor;
import icu.kevin557.eq.utils.render.MinecraftRenderer;
import icu.kevin557.eq.utils.font.FontUtils;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * @author 557
 */
public class HypixelImage extends AbstractImage {

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

    public HypixelImage(EnterallianceQ bot, String id, String name, String karma, String level, String language, String achievementPoints, String guild, String friend, String channel, String firstLogin, boolean status, String lastLogin, String gameType, String mode, String map, String lastLogout, String recent, String ranksGifted) {
        super(bot, ImageUtils.bufferedImage(Objects.requireNonNull(RandomUtils.random(HypixelUtils.MAIN_IMAGES))));
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
        MinecraftRenderer.drawPlayerModel(image, id, "128", "256", null, null, null, 116, 90);

        if (ranksGifted == null) {
            MinecraftRenderer.drawCenterStringWithShadow(image, name, FontUtils.MINECRAFT18, 180, 76);
        }
        else {
            MinecraftRenderer.drawCenterStringWithShadow(image, name, FontUtils.MINECRAFT18, 180, 62);
            MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.YELLOW + this.bot.format("chat.hypixel.ranksGifted") + MinecraftColor.AQUA + ranksGifted, FontUtils.MINECRAFT16, 180, 90);
        }

        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.LIGHT_PURPLE + this.bot.format("chat.hypixel.karma") + MinecraftColor.WHITE + karma, FontUtils.MINECRAFT18, 409, 84);
        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.RED + this.bot.format("chat.hypixel.level") + MinecraftColor.WHITE + level, FontUtils.MINECRAFT18, 409, 118);
        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.GOLD + this.bot.format("chat.hypixel.language") + MinecraftColor.WHITE + language, FontUtils.MINECRAFT18, 409, 152);
        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.YELLOW + this.bot.format("chat.hypixel.achievement") + MinecraftColor.WHITE + achievementPoints, FontUtils.MINECRAFT18, 409, 186);
        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.GREEN + this.bot.format("chat.hypixel.guild") + MinecraftColor.WHITE + guild, FontUtils.MINECRAFT18, 409, 220);
        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.AQUA + this.bot.format("chat.hypixel.friend") + MinecraftColor.WHITE + friend, FontUtils.MINECRAFT18, 409, 254);
        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.BLUE + this.bot.format("chat.hypixel.channel") + MinecraftColor.WHITE + channel, FontUtils.MINECRAFT18, 409, 288);
        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.DARK_PURPLE + this.bot.format("chat.hypixel.join") + MinecraftColor.WHITE + firstLogin, FontUtils.MINECRAFT18, 409, 322);

        if (lastLogin == null) {
            MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.DARK_RED + this.bot.format("chat.hypixel.forbidden"), FontUtils.MINECRAFT18, 637, 407);
        }
        else {
            if (status) {
                MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.status") + MinecraftColor.GREEN + this.bot.format("chat.hypixel.online") + "  " + MinecraftColor.WHITE + lastLogin, FontUtils.MINECRAFT18, 409, 390);

                String session = "";

                if (gameType != null) {
                    session += this.bot.formatByMode("chat.hypixel.gameType", gameType);
                }
                if (mode != null) {
                    session += this.bot.formatByMode("chat.hypixel.mode", mode);
                }
                if (map != null) {
                    session += " " + map;
                }

                MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.at") + MinecraftColor.WHITE + session, FontUtils.MINECRAFT18, 409, 424);
            }
            else {
                MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.status") + MinecraftColor.RED + this.bot.format("chat.hypixel.offline") + (lastLogout == null ? "" : ("  " + MinecraftColor.WHITE + lastLogout)), FontUtils.MINECRAFT18, 409, 390);
                MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.recent") + MinecraftColor.WHITE + this.bot.formatByMode("chat.hypixel.gameType", recent), FontUtils.MINECRAFT18, 409, 424);
            }
        }

        ImageUtils.waterMark(image, image.getWidth() - 126, image.getHeight() - 10, 16F);

        return image;
    }
}
