package icu.kevin557.eq.bots.manager;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.bots.manager.command.*;

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
        this.registerCommand(new TestCommand(this), "test");
        this.registerCommand(new ReloadCommand(this), "reload");
    }

    @Override
    public String getName() {
        return "MNG";
    }
}
