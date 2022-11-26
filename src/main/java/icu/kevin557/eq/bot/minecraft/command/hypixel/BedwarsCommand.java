package icu.kevin557.eq.bot.minecraft.command.hypixel;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.bot.minecraft.MinecraftBot;
import icu.kevin557.eq.bot.minecraft.image.BedwarsImage;
import icu.kevin557.eq.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.Executor;
import icu.kevin557.eq.utils.HttpUtils;
import icu.kevin557.eq.utils.Logger;
import icu.kevin557.eq.utils.games.minecraft.hypixel.HypixelUtils;
import icu.kevin557.eq.utils.games.minecraft.MinecraftColor;
import icu.kevin557.eq.utils.games.minecraft.MinecraftUtils;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;

import java.net.SocketTimeoutException;

/**
 * @author 557
 */
public class BedwarsCommand extends Command {

    public BedwarsCommand(EnterallianceQ bot) {
        super(bot, "hypixel.bedwars");
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        ChatUtils.replay(event, format("chat.waitFor", args[1]));
        if (MinecraftUtils.isNameOrUuid(args[1])) {
            Executor.run(() -> {
                try {
                    String uuid = MinecraftUtils.toUuid(args[1]);
                    JSONObject body = JSON.parseObject(HttpUtils.getString(String.format(HypixelUtils.PLAYER_URL, ((MinecraftBot) this.bot).hypixelKey, uuid)));
                    if (body.getBoolean("success")) {
                        JSONObject player = body.getJSONObject("player");
                        if (player != null) {
                            String rank = HypixelUtils.getRank(player);

                            String name;
                            if (rank == null) {
                                name = MinecraftColor.GRAY.toString();
                            } else {
                                name = rank + " ";
                            }
                            name += player.getString("displayname");

                            int exp = 0;
                            int wins = 0;
                            int losses = 0;
                            int fk = 0;
                            int fd = 0;
                            int kills = 0;
                            int deaths = 0;
                            int bb = 0;
                            int bl = 0;
                            int winstreak = 0;
                            int coins = 0;
                            int boxes = 0;

                            JSONObject stats = player.getJSONObject("stats");
                            if (stats != null) {
                                JSONObject Bedwars = stats.getJSONObject("Bedwars");
                                if (Bedwars != null) {
                                    exp = Bedwars.getIntValue("Experience");
                                    wins = Bedwars.getIntValue("wins_bedwars");
                                    losses = Bedwars.getIntValue("losses_bedwars");
                                    fk = Bedwars.getIntValue("final_kills_bedwars");
                                    fd = Bedwars.getIntValue("final_deaths_bedwars");
                                    kills = Bedwars.getIntValue("kills_bedwars");
                                    deaths = Bedwars.getIntValue("deaths_bedwars");
                                    bb = Bedwars.getIntValue("beds_broken_bedwars");
                                    bl = Bedwars.getIntValue("beds_lost_bedwars");
                                    winstreak = Bedwars.getIntValue("winstreak", -1);
                                    coins = Bedwars.getIntValue("coins");
                                    boxes = Bedwars.getIntValue("bedwars_boxes");
                                }
                            }

                            ChatUtils.replay(event, new BedwarsImage(this.bot, args[1], name, exp, wins, losses, fk, fd, kills, deaths, bb, bl, winstreak, coins, boxes).get(), uuid);
                        }
                        else {
                            ChatUtils.replay(event, format("chat.invalidPlayer"));
                        }
                    } else {
                        if ("Malformed UUID".equals(body.getString("cause"))) {
                            ChatUtils.replay(event, format("chat.invalidPlayer"));
                        } else {
                            ChatUtils.replay(event, format("chat.invalidApi"));
                        }
                    }
                } catch (SocketTimeoutException e) {
                    ChatUtils.replay(event, this.bot.format("chat.exception.timeout", e.getMessage()));
                } catch (Exception e) {
                    ChatUtils.replay(event, this.bot.format("chat.exception.unknown", e.getMessage()));
                    Logger.info("Failed to execute command!", e);
                }
            });
        } else {
            ChatUtils.replay(event, format("chat.wrongPlayer"));
        }
    }

    @Override
    public boolean runnable(User user, int length) {
        return length == 2;
    }
}
