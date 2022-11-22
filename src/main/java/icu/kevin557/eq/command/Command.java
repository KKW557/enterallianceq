package icu.kevin557.eq.command;

import icu.kevin557.eq.EnteralianceQ;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * @author 557
 */
public abstract class Command {

    protected final EnteralianceQ bot;
    protected final String name;

    public Command(EnteralianceQ bot, String name) {
        this.bot = bot;
        this.name = name;
    }

    /**
     * exe
     * @param event event
     * @param args args
     */
    public abstract void execute(MessageEvent event, String[] args);

    /**
     * run?
     * @param user user
     * @param length length
     * @return bool
     */
    public abstract boolean runnable(User user, int length);

    public String description() {
        return this.format("description");
    };

    public String usage() {
        return this.format("usage");
    };

    public String format(String translateKey, Object... params) {
        return this.bot.format("commands." + name + "." + translateKey, params);
    }

    public String getName() {
        return name;
    }
}
