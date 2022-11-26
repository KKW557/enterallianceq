package icu.kevin557.eq.bot.manager;

import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.bot.manager.command.ReloadCommand;

/**
 * @author 557
 */
public class ManagerBot extends EnterallianceQ {

    @Override
    public void registerCommands() {
        super.registerCommands();

        this.registerCommand(new ReloadCommand(this), "reload");
    }

    @Override
    public String getName() {
        return "MNG";
    }
}
