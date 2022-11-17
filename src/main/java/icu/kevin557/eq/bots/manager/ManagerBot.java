package icu.kevin557.eq.bots.manager;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.bots.manager.command.BotsCommand;
import icu.kevin557.eq.bots.manager.command.LoginCommand;
import icu.kevin557.eq.bots.manager.command.LogoutCommand;

/**
 * @author 557
 */
public class ManagerBot extends EqBot {

    @Override
    protected void init() {
        super.init();

        this.registerCommand(new BotsCommand(this), "bots", "bot");
        this.registerCommand(new LoginCommand(this), "login");
        this.registerCommand(new LogoutCommand(this), "logout");
    }

    @Override
    public String getName() {
        return "MNG";
    }
}
