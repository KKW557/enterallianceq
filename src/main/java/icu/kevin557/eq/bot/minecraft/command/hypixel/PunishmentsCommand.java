package icu.kevin557.eq.bot.minecraft.command.hypixel;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.bot.minecraft.MinecraftBot;
import icu.kevin557.eq.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.Executor;
import icu.kevin557.eq.utils.HttpUtils;
import icu.kevin557.eq.utils.Logger;
import icu.kevin557.eq.utils.format.FormatUtils;
import icu.kevin557.eq.utils.games.minecraft.hypixel.HypixelUtils;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;

import java.net.SocketTimeoutException;

/**
 * @author 557
 */
public class PunishmentsCommand extends Command {

    public PunishmentsCommand(EnterallianceQ bot) {
        super(bot, "hypixel.punishments");
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        Executor.run(() -> {
            try {
                JSONObject body = JSON.parseObject(HttpUtils.getString(String.format(HypixelUtils.PUNISHMENTS_URL, ((MinecraftBot) this.bot).hypixelKey)));
                if (body.getBoolean("success")) {
                    String message = format("watchdogLastMinute") + FormatUtils.formatNumber(body.getInteger("watchdog_lastMinute")) + '\n' +
                            format("watchdogRollingDaily") + FormatUtils.formatNumber(body.getInteger("watchdog_rollingDaily")) + '\n' +
                            format("watchdogTotal") + FormatUtils.formatNumber(body.getInteger("watchdog_total")) + '\n' +
                            format("staffRollingDaily") + FormatUtils.formatNumber(body.getInteger("staff_rollingDaily")) + '\n' +
                            format("staffTotal") + FormatUtils.formatNumber(body.getInteger("staff_total"));
                    ChatUtils.replay(event, message);
                }
                else {
                    ChatUtils.replay(event, format("chat.invalidApi"));
                }
            } catch (SocketTimeoutException e) {
                ChatUtils.replay(event, this.bot.format("chat.exception.timeout", e.getMessage()));
            } catch (Exception e) {
                ChatUtils.replay(event, this.bot.format("chat.exception.unknown", e.getMessage()));
                Logger.info("Failed to execute command!", e);
            }
        });
    }

    @Override
    public boolean runnable(User user, int length) {
        return length <= 2;
    }
}
