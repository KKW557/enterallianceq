package icu.kevin557.eq.bots.manager.command;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.api.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.I18n;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;

/**
 * @author 557
 */
public class BotsCommand extends Command {

    public BotsCommand(EqBot bot) {
        super(bot);
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        MessageChainBuilder messageBuilder = new MessageChainBuilder();
        int i = 0;
        int j = 0;
        for (EqBot bot : EqBot.Manager.BOTS) {
            if (bot.getBot().isOnline()) {
                i++;
            }
            else {
                j++;
            }
        }
        messageBuilder.append(I18n.format("command.bots.online", i));
        if (i != 0) {
            for (EqBot bot : EqBot.Manager.BOTS) {
                if (bot.getBot().isOnline()) {
                    messageBuilder.append('\n').append(I18n.format("command.bots.bot", bot.getName(), bot.getBot().getId()));
                }
            }
        }
        messageBuilder.append('\n').append(I18n.format("command.bots.offline", j));
        if (j != 0) {
            for (EqBot bot : EqBot.Manager.BOTS) {
                if (!bot.getBot().isOnline()) {
                    messageBuilder.append('\n').append(I18n.format("command.bots.bot", bot.getName(), bot.getBot().getId()));
                }
            }
        }

        ChatUtils.replay(event, messageBuilder.build());
    }

    @Override
    public String description() {
        return I18n.format("command.bots.description");
    }

    @Override
    public String[] usages() {
        return new String[] {I18n.format("command.bots.usage1")};
    }
}
