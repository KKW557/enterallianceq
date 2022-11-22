package icu.kevin557.eq.bot.minecraft.image;

import icu.kevin557.eq.EnteralianceQ;
import icu.kevin557.eq.image.AbstractImage;
import icu.kevin557.eq.utils.ImageUtils;
import icu.kevin557.eq.utils.math.RandomUtils;
import icu.kevin557.eq.utils.minecraft.HypixelUtils;
import icu.kevin557.eq.utils.minecraft.MinecraftColor;
import icu.kevin557.eq.utils.minecraft.MinecraftUtils;
import icu.kevin557.eq.utils.resouces.FontUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * @author 557
 */
public class HypixelImage extends AbstractImage {

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

    public HypixelImage(EnteralianceQ bot, String id, String name, String karma, String level, String language, String achievementPoints, String guild, String friend, String channel, String firstLogin, boolean status, String lastLogin, String gameType, String mode, String map, String lastLogout, String recent, String ranksGifted) {
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

        BufferedImage playerModel = MinecraftUtils.playerModel(id, 128, 256, null, null, null);
        if (playerModel != null) {
            ImageUtils.drawImage(image, playerModel, 116, 90);
        }

        if (ranksGifted == null) {
            this.drawCenterStringWithShadow(name, FONT18, 180, 76);
        }
        else {
            this.drawCenterStringWithShadow(name, FONT18, 180, 62);
            this.drawCenterStringWithShadow(MinecraftColor.YELLOW + this.bot.format("chat.hypixel.ranksGifted") + MinecraftColor.AQUA + ranksGifted, FONT16, 180, 90);
        }

        this.drawStringWithShadow(MinecraftColor.LIGHT_PURPLE + this.bot.format("chat.hypixel.karma") + MinecraftColor.WHITE + karma, FONT18, 409, 84);
        this.drawStringWithShadow(MinecraftColor.RED + this.bot.format("chat.hypixel.level") + MinecraftColor.WHITE + level, FONT18, 409, 118);
        this.drawStringWithShadow(MinecraftColor.GOLD + this.bot.format("chat.hypixel.language") + MinecraftColor.WHITE + language, FONT18, 409, 152);
        this.drawStringWithShadow(MinecraftColor.YELLOW + this.bot.format("chat.hypixel.achievement") + MinecraftColor.WHITE + achievementPoints, FONT18, 409, 186);
        this.drawStringWithShadow(MinecraftColor.GREEN + this.bot.format("chat.hypixel.guild") + MinecraftColor.WHITE + guild, FONT18, 409, 220);
        this.drawStringWithShadow(MinecraftColor.AQUA + this.bot.format("chat.hypixel.friend") + MinecraftColor.WHITE + friend, FONT18, 409, 254);
        this.drawStringWithShadow(MinecraftColor.BLUE + this.bot.format("chat.hypixel.channel") + MinecraftColor.WHITE + channel, FONT18, 409, 288);
        this.drawStringWithShadow(MinecraftColor.DARK_PURPLE + this.bot.format("chat.hypixel.join") + MinecraftColor.WHITE + firstLogin, FONT18, 409, 322);

        if (lastLogin == null) {
            this.drawCenterStringWithShadow(MinecraftColor.DARK_RED + this.bot.format("chat.hypixel.forbidden"), FONT18, 637, 407);
        }
        else {
            if (status) {
                this.drawStringWithShadow(this.bot.format("chat.hypixel.status") + MinecraftColor.GREEN + this.bot.format("chat.hypixel.online") + "  " + MinecraftColor.DARK_GRAY + lastLogin, FONT18, 409, 390);

                String session = "";

                if (gameType != null) {
                    session += this.bot.format("chat.hypixel.gameType." + gameType);
                }
                if (mode != null) {
                    session += this.bot.format("chat.hypixel.mode." + mode);
                }
                if (map != null) {
                    session += " " + map;
                }

                this.drawStringWithShadow(this.bot.format("chat.hypixel.at") + MinecraftColor.GRAY + session, FONT18, 409, 424);
            }
            else {
                this.drawStringWithShadow(this.bot.format("chat.hypixel.status") + MinecraftColor.RED + this.bot.format("chat.hypixel.offline") + (lastLogout == null ? "" : ("  " + MinecraftColor.DARK_GRAY + lastLogout)), FONT18, 409, 390);
                this.drawStringWithShadow(this.bot.format("chat.hypixel.recent") + MinecraftColor.GRAY + this.bot.format("chat.hypixel.gameType." + recent), FONT18, 409, 424);
            }
        }

        return image;
    }

    private void drawCenterStringWithShadow(String str, Font font, float x, float y) {
        ImageUtils.drawCenterString(image, FontUtils.minecraftColorString(str, font, false), x + 1, y + 1);
        ImageUtils.drawCenterString(image, FontUtils.minecraftColorString(str, font, true), x - 1, y - 1);
    }

    private void drawStringWithShadow(String str, Font font, float x, float y) {
        ImageUtils.drawString(image, FontUtils.minecraftColorString(str, font, false), x + 1, y + 1);
        ImageUtils.drawString(image, FontUtils.minecraftColorString(str, font, true), x - 1, y - 1);
    }
}
