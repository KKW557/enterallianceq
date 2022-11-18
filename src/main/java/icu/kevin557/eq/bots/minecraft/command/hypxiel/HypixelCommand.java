package icu.kevin557.eq.bots.minecraft.command.hypxiel;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.api.command.Command;
import icu.kevin557.eq.api.command.Logger;
import icu.kevin557.eq.bots.minecraft.image.hypixel.HypixelImageBuilder;
import icu.kevin557.eq.utils.*;
import icu.kevin557.eq.utils.MinecraftUtils;
import net.mamoe.mirai.event.events.MessageEvent;

import java.math.BigDecimal;
import java.net.URL;

/**
 * @author 557
 */
public class HypixelCommand extends Command {

    public static final String URL = "https://api.hypixel.net/player?key=%s&uuid=%s";

    public HypixelCommand(EqBot bot) {
        super(bot);
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        if (args.length == 2) {
            Executor.run(() -> {
                try {
                    if (MinecraftUtils.isNameOrUuid(args[1])) {
                        String uuid = MinecraftUtils.toUuid(args[1]);
                        JSONObject body = JSON.parseObject(new URL(String.format(URL, ConfigUtils.HYPIXEL_KEY, uuid)));
                        if (body.getBoolean("success")) {
                            JSONObject player = body.getJSONObject("player");
                            if (player != null) {
                                String name = player.getString("displayname");
                                double level = formatLevel(player.getBigDecimal("networkExp"));
                                int karma = player.getIntValue("karma");
                                HypixelImageBuilder image = new HypixelImageBuilder(name, level + "", karma + "");
                                ChatUtils.replay(event, image.build(), uuid);
                            }
                            else {
                                ChatUtils.replay(event, I18n.format("minecraft.notFound"));
                            }
                        }
                        else {
                            ChatUtils.replay(event, I18n.format("command.hypixel.apiInvalid"));
                        }
                    }
                    else {
                        ChatUtils.replay(event, I18n.format("minecraft.wrong"));
                    }
                }
                catch (Exception e) {
                    ChatUtils.replay(event, I18n.format("chat.exception", e.getMessage()));
                    Logger.info("Failed to execute command!", e);
                }
            });
        }
    }

    private static double formatLevel(BigDecimal exp) {
        return Math.sqrt(0.0008 * exp.floatValue() + 12.25) - 2.5;
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
