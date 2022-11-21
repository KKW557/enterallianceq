package icu.kevin557.eq.bots.minecraft.command.hypxiel;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.api.command.Command;
import icu.kevin557.eq.api.command.Logger;
import icu.kevin557.eq.bots.minecraft.image.hypixel.HypixelImage;
import icu.kevin557.eq.utils.*;
import icu.kevin557.eq.utils.minecraft.HypixelUtils;
import icu.kevin557.eq.utils.minecraft.MinecraftUtils;
import net.mamoe.mirai.event.events.MessageEvent;

import java.net.SocketTimeoutException;

/**
 * @author 557
 */
public class HypixelCommand extends Command {

    public HypixelCommand(EqBot bot) {
        super(bot);
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        if (args.length == 2) {
            ChatUtils.replay(event, I18n.format("command.hypixel.wait", args[1]));
            Executor.run(() -> {
                try {
                    if (MinecraftUtils.isNameOrUuid(args[1])) {
                        String uuid = MinecraftUtils.toUuid(args[1]);
                        JSONObject body = JSON.parseObject(HttpUtils.executeString(String.format(HypixelUtils.PLAYER_URL, ConfigUtils.HYPIXEL_KEY, uuid)));
                        if (body.getBoolean("success")) {
                            JSONObject player = body.getJSONObject("player");
                            if (player != null) {

                                String rank = null;
                                if (player.get("rank") != null && !"NONE".equals(player.getString("rank"))) {
                                    rank = HypixelUtils.Rank.getRank(player.getString("rank"));
                                }
                                if (player.get("prefix") != null && !"NONE".equals(player.getString("prefix"))) {
                                    rank = player.getString("prefix");
                                }
                                else if (rank == null) {
                                    String plusColor = player.get("rankPlusColor") == null ? MinecraftUtils.Color.RED.toString() : MinecraftUtils.Color.getColor(player.getString("rankPlusColor")).toString();
                                    if (player.get("monthlyPackageRank") != null && !"NONE".equals(player.getString("monthlyPackageRank"))) {
                                        String rankColor = player.get("monthlyRankColor") == null ? MinecraftUtils.Color.GOLD.toString() : MinecraftUtils.Color.getColor(player.getString("monthlyRankColor")).toString();
                                        rank = HypixelUtils.Rank.getRank(player.getString("monthlyPackageRank"), rankColor, plusColor);
                                    }
                                    else {
                                        if (player.get("packageRank") != null && !"NONE".equals(player.getString("packageRank"))) {
                                            rank = HypixelUtils.Rank.getRank(player.getString("packageRank"), plusColor);
                                        }
                                        if (player.get("newPackageRank") != null && !"NONE".equals(player.getString("newPackageRank"))) {
                                            rank = HypixelUtils.Rank.getRank(player.getString("newPackageRank"), plusColor);
                                        }
                                    }
                                }

                                String guild = I18n.format("chat.hypixel.none");
                                String tag = null;
                                JSONObject guildJson = JSON.parseObject(HttpUtils.executeString(String.format(HypixelUtils.GUILD_URL, ConfigUtils.HYPIXEL_KEY, uuid)));
                                JSONObject guildObject = guildJson.getJSONObject("guild");
                                if (guildJson.getBoolean("success") && guildObject != null) {
                                    guild = guildObject.getString("name");
                                    if (guildObject.get("tag") != null) {
                                        tag = (guildObject.get("tagColor") == null ? MinecraftUtils.Color.GRAY : MinecraftUtils.Color.getColor(guildObject.getString("tagColor"))).toString() + '[' + guildObject.getString("tag") + ']';
                                    }
                                }

                                String name;
                                if (rank == null) {
                                    name = MinecraftUtils.Color.GRAY.toString();
                                }
                                else {
                                    name = rank + " ";
                                }
                                name += player.getString("displayname");
                                if (tag != null) {
                                    name += (" " + tag);
                                }

                                String karma = FormatUtils.formatNumber(player.getIntValue("karma"));

                                String level = FormatUtils.formatNumber(HypixelUtils.formatNetworkLevel(player.getDoubleValue("networkExp")));

                                String language = I18n.formatWithMode("chat.hypixel.language", (player.get("userLanguage") == null ? "ENGLISH" : player.getString("userLanguage")));

                                String achievementPoints = FormatUtils.formatNumber(player.getIntValue("achievementPoints"));

                                String friend = "0";
                                JSONObject friendJson = JSON.parseObject(HttpUtils.executeString(String.format(HypixelUtils.FRIEND_URL, ConfigUtils.HYPIXEL_KEY, uuid)));
                                if (friendJson.getBoolean("success")) {
                                    friend = FormatUtils.formatNumber(friendJson.getJSONArray("records").size());
                                }

                                String channel = I18n.formatWithMode("chat.hypixel.channel", (player.get("channel") == null ? "ALL" : player.getString("channel")));

                                String firstLogin = DateUtils.formatChina(player.getLongValue("firstLogin"));

                                boolean status = false;

                                String gameType = null;
                                String mode = null;
                                String map = null;

                                JSONObject statusJson = JSON.parseObject(HttpUtils.executeString(String.format(HypixelUtils.STATUS_URL, ConfigUtils.HYPIXEL_KEY, uuid)));
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

                                ChatUtils.replay(event, new HypixelImage(args[1], name, karma, level, language, achievementPoints, guild, friend, channel, firstLogin, status, lastLogin, gameType, mode, map, lastLogout, recent, ranksGifted).get(), uuid);
                            }
                            else {
                                ChatUtils.replay(event, I18n.format("chat.minecraft.notFound"));
                            }
                        }
                        else {
                            if ("Malformed UUID".equals(body.getString("cause"))) {
                                ChatUtils.replay(event, I18n.format("chat.minecraft.notFound"));
                            }
                            else {
                                ChatUtils.replay(event, I18n.format("command.hypixel.apiInvalid"));
                            }
                        }
                    }
                    else {
                        ChatUtils.replay(event, I18n.format("chat.minecraft.wrong"));
                    }
                }
                catch (SocketTimeoutException e) {
                    ChatUtils.replay(event, I18n.format("chat.exception.timeout", e.getMessage()));
                }
                catch (Exception e) {
                    ChatUtils.replay(event, I18n.format("chat.exception.unknown", e.getMessage()));
                    Logger.info("Failed to execute command!", e);
                }
            });
        }
    }

    @Override
    public String description() {
        return I18n.format("command.hypixel.description");
    }

    @Override
    public String[] usages() {
        return new String[] {I18n.format("command.hypixel.usage1")};
    }
}
