package icu.kevin557.eq.bots.manager.command;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.api.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.I18n;
import icu.kevin557.eq.utils.MathUtils;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * @author 557
 */
public class LogoutCommand extends Command {

    public LogoutCommand(EqBot bot) {
        super(bot);
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        if (args.length == 2) {
            String qq = args[1];
            if (MathUtils.isLong(qq)) {
                EqBot bot = EqBot.Manager.getBot(Long.parseLong(qq));

                if (bot == null) {
                    ChatUtils.replay(event, I18n.format("command.logout.notFound", qq));
                }
                else {
                    bot.logout();
                    ChatUtils.replay(event, I18n.format("command.logout.logout", qq));
                }
            }
            else {
                ChatUtils.replay(event, I18n.format("command.logout.notQQ", qq));
            }
        }
    }

    @Override
    public String description() {
        return I18n.format("command.logout.description");
    }

    @Override
    public String[] usages() {
        return new String[] {I18n.format("command.logout.usage1")};
    }
}
