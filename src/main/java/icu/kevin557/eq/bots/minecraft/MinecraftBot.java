package icu.kevin557.eq.bots.minecraft;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.bots.minecraft.command.hypxiel.HypixelCommand;

/**
 * @author 557
 */
public class MinecraftBot extends EqBot {

    @Override
    protected void init() {
        super.init();

        this.registerCommand(new HypixelCommand(this), "hypixel", "hyp");
    }

    @Override
    public String getName() {
        return "MC";
    }
}
