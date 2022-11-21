package icu.kevin557.eq.bots.minecraft.command.hypxiel;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.api.command.Command;
import icu.kevin557.eq.utils.I18n;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * @author 557
 */
public class BedwarsCommand extends Command {

    public BedwarsCommand(EqBot bot) {
        super(bot);
    }

    @Override
    public void execute(MessageEvent event, String[] args) {

    }

    @Override
    public String description() {
        return I18n.format("command.hypixel.bedwars.description");
    }

    @Override
    public String[] usages() {
        return new String[] {I18n.format("command.hypixel.bedwars.usage1")};
    }
}
