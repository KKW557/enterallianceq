package icu.kevin557.eq.bot.manager.command;

import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.EqManager;
import icu.kevin557.eq.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.games.minecraft.hypixel.HypixelUtils;
import icu.kevin557.eq.utils.resouces.I18n;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * @author 557
 */
public class ReloadCommand extends Command {

    public ReloadCommand(EnterallianceQ bot) {
        super(bot, "reload");
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        I18n.loadLanguages();
        HypixelUtils.loadImages();
        EqManager.loadBotConfigs();

        ChatUtils.replay(event, format("chat.done"));
    }

    @Override
    public boolean runnable(User user, int length) {
        return this.bot.isAdmin(user);
    }
}
