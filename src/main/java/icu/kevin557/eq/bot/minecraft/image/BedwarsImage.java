package icu.kevin557.eq.bot.minecraft.image;

import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.image.AbstractImage;
import icu.kevin557.eq.utils.format.FormatUtils;
import icu.kevin557.eq.utils.games.minecraft.MinecraftColor;
import icu.kevin557.eq.utils.games.minecraft.hypixel.bedwars.BedwarsPrestige;
import icu.kevin557.eq.utils.games.minecraft.hypixel.bedwars.BedwarsUtils;
import icu.kevin557.eq.utils.render.ImageUtils;
import icu.kevin557.eq.utils.math.RandomUtils;
import icu.kevin557.eq.utils.games.minecraft.hypixel.HypixelUtils;
import icu.kevin557.eq.utils.render.MinecraftRenderer;
import icu.kevin557.eq.utils.font.FontUtils;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @author 557
 */
public class BedwarsImage extends AbstractImage {

    // region info

    private final String id;
    private final String name;
    private final int exp;
    
    private final int wins;
    private final int losses;

    private final int fk;
    private final int fd;

    private final int kills;
    private final int deaths;

    private final int bb;
    private final int bl;

    private final int winstreak;

    private final int coins;
    private final int boxes;

    // endregion

    public BedwarsImage(EnterallianceQ bot, String id, String name, int exp, int wins, int losses, int fk, int fd, int kills, int deaths, int bb, int bl, int winstreak, int coins, int boxes) {
        super(bot, ImageUtils.bufferedImage(Objects.requireNonNull(RandomUtils.random(HypixelUtils.BEDWARS_IMAGES))));
        this.id = id;
        this.name = name;
        this.exp = exp;
        this.wins = wins;
        this.losses = losses;
        this.fk = fk;
        this.fd = fd;
        this.kills = kills;
        this.deaths = deaths;
        this.bb = bb;
        this.bl = bl;
        this.winstreak = winstreak;
        this.coins = coins;
        this.boxes = boxes;
    }

    @Override
    public BufferedImage get() {
        MinecraftRenderer.drawPlayerModel(image, id, "128", "256", null, null, null, 116, 90);

        MinecraftRenderer.drawCenterStringWithShadow(image, name, FontUtils.MINECRAFT18, 180, 76);

        int level = BedwarsUtils.getLevelForExp(exp);
        String levelTag = BedwarsPrestige.format(BedwarsUtils.getPrestigeForLevel(level), level);

        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.level") + levelTag, FontUtils.MINECRAFT18, 114, 424);
        MinecraftRenderer.drawStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.process") + MinecraftColor.AQUA + FormatUtils.formatNumber(BedwarsUtils.getOverflowExp(exp)) + MinecraftColor.GRAY + "/" + MinecraftColor.GREEN + FormatUtils.formatBigNumber(BedwarsUtils.getExpForLevel(level)), FontUtils.MINECRAFT18, 114, 460);

        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.wins"), FontUtils.MINECRAFT16, 494, 74);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GREEN + FormatUtils.formatNumber(wins), FontUtils.MINECRAFT24, 494, 104);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.wlr"), FontUtils.MINECRAFT16, 637, 74);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GOLD + FormatUtils.formatRatioForNumber(wins, losses), FontUtils.MINECRAFT24, 637, 104);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.losses"), FontUtils.MINECRAFT16, 770, 74);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.RED + FormatUtils.formatNumber(losses), FontUtils.MINECRAFT24, 770, 104);

        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.fk"), FontUtils.MINECRAFT16, 494, 158);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GREEN + FormatUtils.formatNumber(fk), FontUtils.MINECRAFT24, 494, 188);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.fkdr"), FontUtils.MINECRAFT16, 637, 158);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GOLD + FormatUtils.formatRatioForNumber(fk, fd), FontUtils.MINECRAFT24, 637, 188);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.fd"), FontUtils.MINECRAFT16, 770, 158);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.RED + FormatUtils.formatNumber(fd), FontUtils.MINECRAFT24, 770, 188);

        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.kills"), FontUtils.MINECRAFT16, 494, 242);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GREEN + FormatUtils.formatNumber(kills), FontUtils.MINECRAFT24, 494, 272);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.kdr"), FontUtils.MINECRAFT16, 637, 242);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GOLD + FormatUtils.formatRatioForNumber(kills, deaths), FontUtils.MINECRAFT24, 637, 272);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.deaths"), FontUtils.MINECRAFT16, 770, 242);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.RED + FormatUtils.formatNumber(deaths), FontUtils.MINECRAFT24, 770, 272);

        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.bb"), FontUtils.MINECRAFT16, 494, 326);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GREEN + FormatUtils.formatNumber(bb), FontUtils.MINECRAFT24, 494, 356);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.bblr"), FontUtils.MINECRAFT16, 637, 326);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GOLD + FormatUtils.formatRatioForNumber(bb, bl), FontUtils.MINECRAFT24, 637, 356);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.bl"), FontUtils.MINECRAFT16, 770, 326);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.RED + FormatUtils.formatNumber(bl), FontUtils.MINECRAFT24, 770, 356);

        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.coins"), FontUtils.MINECRAFT16, 494, 410);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GOLD + FormatUtils.formatBigNumber(coins), FontUtils.MINECRAFT24, 494, 440);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.winstreak"), FontUtils.MINECRAFT16, 637, 410);
        String winstreakText = this.bot.format("chat.hypixel.unknown");
        if (winstreak != -1) {
            if (winstreak < 10) {
                winstreakText = MinecraftColor.WHITE.toString();
            }
            else if (winstreak < 20) {
                winstreakText = MinecraftColor.GOLD.toString();
            }
            else if (winstreak < 35) {
                winstreakText = MinecraftColor.DARK_GREEN.toString();
            }
            else if (winstreak < 50) {
                winstreakText = MinecraftColor.RED.toString();
            }
            else if (winstreak < 75) {
                winstreakText = MinecraftColor.DARK_RED.toString();
            }
            else if (winstreak < 100) {
                winstreakText = MinecraftColor.LIGHT_PURPLE.toString();
            }
            else {
                winstreakText = MinecraftColor.DARK_PURPLE.toString();
            }
            winstreakText += FormatUtils.formatNumber(winstreak);
        }
        MinecraftRenderer.drawCenterStringWithShadow(image, winstreakText, FontUtils.MINECRAFT24, 637, 440);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.GRAY + this.bot.format("chat.hypixel.bedwars.boxes"), FontUtils.MINECRAFT16, 770, 410);
        MinecraftRenderer.drawCenterStringWithShadow(image, MinecraftColor.YELLOW + FormatUtils.formatBigNumber(boxes), FontUtils.MINECRAFT24, 770, 440);

        ImageUtils.waterMark(image, image.getWidth() - 126, image.getHeight() - 10, 16F);

        return image;
    }
}
