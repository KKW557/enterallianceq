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
public class LoginCommand extends Command {

    public LoginCommand(EqBot bot) {
        super(bot);
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        if (args.length == 2) {
            String qq = args[1];
            if (MathUtils.isLong(qq)) {
                EqBot bot = EqBot.Manager.getBot(Long.parseLong(qq));

                if (bot == null) {
                    ChatUtils.replay(event, I18n.format("command.login.notFound", qq));
                }
                else {
                    bot.login();
                    ChatUtils.replay(event, I18n.format("command.login.login", qq));
                }
            }
            else {
                ChatUtils.replay(event, I18n.format("command.login.notQQ", qq));
            }
        }
    }

    @Override
    public String description() {
        return I18n.format("command.login.description");
    }

    @Override
    public String[] usages() {
        return new String[] {I18n.format("command.login.usage1")};
    }
}
