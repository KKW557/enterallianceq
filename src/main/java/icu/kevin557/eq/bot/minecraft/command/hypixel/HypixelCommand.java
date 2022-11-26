package icu.kevin557.eq.bot.minecraft.command.hypixel;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.bot.minecraft.MinecraftBot;
import icu.kevin557.eq.bot.minecraft.image.HypixelImage;
import icu.kevin557.eq.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.Executor;
import icu.kevin557.eq.utils.HttpUtils;
import icu.kevin557.eq.utils.Logger;
import icu.kevin557.eq.utils.format.DateUtils;
import icu.kevin557.eq.utils.format.FormatUtils;
import icu.kevin557.eq.utils.games.minecraft.hypixel.HypixelUtils;
import icu.kevin557.eq.utils.games.minecraft.MinecraftColor;
import icu.kevin557.eq.utils.games.minecraft.MinecraftUtils;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;

import java.net.SocketTimeoutException;

/**
 * @author 557
 */
public class HypixelCommand extends Command {

    public HypixelCommand(MinecraftBot bot) {
        super(bot, "hypixel");
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
                            String guild = this.bot.format("chat.hypixel.none");
                            String tag = null;
                            JSONObject guildJson = JSON.parseObject(HttpUtils.getString(String.format(HypixelUtils.GUILD_URL, ((MinecraftBot) this.bot).hypixelKey, uuid)));
                            JSONObject guildObject = guildJson.getJSONObject("guild");
                            if (guildJson.getBoolean("success") && guildObject != null) {
                                guild = guildObject.getString("name");
                                if (guildObject.get("tag") != null) {
                                    tag = (guildObject.get("tagColor") == null ? MinecraftColor.GRAY : MinecraftColor.getColor(guildObject.getString("tagColor"))).toString() + '[' + guildObject.getString("tag") + ']';
                                }
                            }
                            String name;
                            if (rank == null) {
                                name = MinecraftColor.GRAY.toString();
                            } else {
                                name = rank + " ";
                            }
                            name += player.getString("displayname");
                            if (tag != null) {
                                name += (" " + tag);
                            }
                            String karma = FormatUtils.formatBigNumber(player.getIntValue("karma"));
                            String level = FormatUtils.formatNumber(HypixelUtils.formatNetworkLevel(player.getDoubleValue("networkExp")));
                            String language = this.bot.format("chat.hypixel.language." + (player.get("userLanguage") == null ? "ENGLISH" : player.getString("userLanguage")));
                            String achievementPoints = FormatUtils.formatNumber(player.getIntValue("achievementPoints"));
                            String friend = "0";
                            JSONObject friendJson = JSON.parseObject(HttpUtils.getString(String.format(HypixelUtils.FRIEND_URL, ((MinecraftBot) this.bot).hypixelKey, uuid)));
                            if (friendJson.getBoolean("success")) {
                                friend = FormatUtils.formatNumber(friendJson.getJSONArray("records").size());
                            }
                            String channel = this.bot.formatByMode("chat.hypixel.channel", (player.get("channel") == null ? "ALL" : player.getString("channel")));
                            String firstLogin = DateUtils.formatChina(player.getLongValue("firstLogin"));
                            boolean status = false;
                            String gameType = null;
                            String mode = null;
                            String map = null;
                            JSONObject statusJson = JSON.parseObject(HttpUtils.getString(String.format(HypixelUtils.STATUS_URL, ((MinecraftBot) this.bot).hypixelKey, uuid)));
                            if (statusJson.getBoolean("success")) {
                                JSONObject sessionObject = statusJson.getJSONObject("session");
                                if (sessionObject.getBoolean("online")) {
                                    status = true;
                                    gameType = sessionObject.getString("gameType");
                                    mode = sessionObject.getString("mode");
                                    map = sessionObject.getString("map");
                                    if (mode != null) {
                                        if (mode.contains(gameType)) {
                                            gameType = null;
                                        }
                                    }
                                    if (map != null) {
                                        if (map.equalsIgnoreCase(mode)) {
                                            mode = null;
                                        }
                                    }
                                }
                            }
                            String lastLogin = player.get("lastLogin") == null ? null : DateUtils.formatChina(player.getLong("lastLogin"));
                            String lastLogout = player.get("lastLogout") == null ? null : DateUtils.formatChina(player.getLong("lastLogout"));
                            String recent = player.getString("mostRecentGameType");
                            String ranksGifted = null;
                            JSONObject giftingMeta = player.getJSONObject("giftingMeta");
                            if (giftingMeta != null && giftingMeta.get("ranksGiven") != null) {
                                ranksGifted = FormatUtils.formatNumber(giftingMeta.getInteger("ranksGiven"));
                            }
                            ChatUtils.replay(event, new HypixelImage(this.bot, args[1], name, karma, level, language, achievementPoints, guild, friend, channel, firstLogin, status, lastLogin, gameType, mode, map, lastLogout, recent, ranksGifted).get(), uuid);
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
