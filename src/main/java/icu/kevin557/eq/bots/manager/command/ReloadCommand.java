package icu.kevin557.eq.bots.manager.command;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.api.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.ConfigUtils;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * @author 557
 */
public class ReloadCommand extends Command {

    public ReloadCommand(EqBot bot) {
        super(bot);
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        ConfigUtils.load();
        ChatUtils.replay(event, "Reloaded configs.");
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public String[] usages() {
        return new String[0];
    }
}
