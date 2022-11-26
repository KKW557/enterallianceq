package icu.kevin557.eq.bot.minecraft.command.hypixel;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.bot.minecraft.MinecraftBot;
import icu.kevin557.eq.bot.minecraft.image.SkywarsImage;
import icu.kevin557.eq.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.Executor;
import icu.kevin557.eq.utils.HttpUtils;
import icu.kevin557.eq.utils.Logger;
import icu.kevin557.eq.utils.games.minecraft.MinecraftColor;
import icu.kevin557.eq.utils.games.minecraft.MinecraftUtils;
import icu.kevin557.eq.utils.games.minecraft.hypixel.HypixelUtils;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;

import java.net.SocketTimeoutException;

/**
 * @author 557
 */
public class SkywarsCommand extends Command {

    public SkywarsCommand(EnterallianceQ bot) {
        super(bot, "hypixel.skywars");
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

                            String level = "";

                            JSONObject stats = player.getJSONObject("stats");
                            if (stats != null) {
                                JSONObject SkyWars = stats.getJSONObject("SkyWars");
                                if (SkyWars != null) {
                                    level = SkyWars.getString("levelFormatted");
                                }
                            }

                            ChatUtils.replay(event, new SkywarsImage(this.bot, args[1], name, level).get(), uuid);
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
