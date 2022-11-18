package icu.kevin557.eq.bots.manager.command;

import icu.kevin557.eq.api.bot.EqBot;
import icu.kevin557.eq.api.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.ImageUtils;
import net.mamoe.mirai.event.events.MessageEvent;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author 557
 */
public class TestCommand extends Command {

    public TestCommand(EqBot bot) {
        super(bot);
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        ImageUtils.drawRect(image, 0, 0, 100, 100, Color.CYAN);
        ChatUtils.replay(event, image, "test");
    }

    @Override
    public String description() {
        return "test";
    }

    @Override
    public String[] usages() {
        return new String[0];
    }
}
