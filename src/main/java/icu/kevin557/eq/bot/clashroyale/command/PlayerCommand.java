package icu.kevin557.eq.bot.clashroyale.command;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.bot.clashroyale.ClashroyaleBot;
import icu.kevin557.eq.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.Executor;
import icu.kevin557.eq.utils.HttpUtils;
import icu.kevin557.eq.utils.Logger;
import icu.kevin557.eq.utils.games.clashroyale.ClashroyaleUtils;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;
import okhttp3.Request;

import java.net.SocketTimeoutException;

/**
 * @author 557
 */
public class PlayerCommand extends Command {

    public PlayerCommand(EnterallianceQ bot) {
        super(bot, "clashroyale");
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        ChatUtils.replay(event, format("chat.waitFor", args[1]));
        if (ClashroyaleUtils.isTag(args[1])) {
            Executor.run(() -> {
                try {
                    String tag = ClashroyaleUtils.transTag(args[1]);
                    JSONObject body = JSON.parseObject(HttpUtils.getString(new Request.Builder().url(String.format(ClashroyaleUtils.CHEST_URL, tag)).header("Authorization", "Bearer " + ((ClashroyaleBot) this.bot).token).build()));
                    if (body.get("reason") == null) {
                    } else {
                        if ("notFound".equals(body.getString("reason")) && body.get("message") == null) {
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
            ChatUtils.replay(event, format("wrongTag"));
        }
    }

    @Override
    public boolean runnable(User user, int length) {
        return length == 2;
    }
}
