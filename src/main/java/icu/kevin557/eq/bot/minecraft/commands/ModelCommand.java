package icu.kevin557.eq.bot.minecraft.commands;

import icu.kevin557.eq.EnteralianceQ;
import icu.kevin557.eq.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.Executor;
import icu.kevin557.eq.utils.math.MathUtils;
import icu.kevin557.eq.utils.minecraft.MinecraftUtils;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;

import java.awt.image.BufferedImage;

/**
 * @author 557
 */
public class ModelCommand extends Command {

    public ModelCommand(EnteralianceQ bot) {
        super(bot, "model");
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        Executor.run(() -> {
            if (MinecraftUtils.isNameOrUuid(args[1])) {
                String name = MinecraftUtils.toName(args[1]);

                int width;
                if (MathUtils.isInteger(args[2])) {
                    width = Integer.parseInt(args[2]);
                }
                else {
                    ChatUtils.replay(event, this.bot.format("chat.exception.illegalArg", this.bot.prefix + "help " + getName()));
                    return;
                }

                int height;
                if (MathUtils.isInteger(args[3])) {
                    height = Integer.parseInt(args[3]);
                }
                else {
                    ChatUtils.replay(event, this.bot.format("chat.exception.illegalArg", this.bot.prefix + "help " + getName()));
                    return;
                }

                String time = null;
                String theta = null;
                String phi = null;

                for (int i = 4; i < args.length; i++) {
                    if (i % 2 == 0) {
                        if ("-time".equals(args[i])) {
                            if (MathUtils.isDouble(args[i+1])) {
                                time = args[i+1];
                            }
                            else {
                                ChatUtils.replay(event, this.bot.format("chat.exception.illegalArg", this.bot.prefix + "help " + getName()));
                                return;
                            }
                        }
                        else if ("-theta".equals(args[i])) {
                            if (MathUtils.isDouble(args[i+1])) {
                                theta = args[i+1];
                            }
                            else {
                                ChatUtils.replay(event, this.bot.format("chat.exception.illegalArg", this.bot.prefix + "help " + getName()));
                                return;
                            }
                        }
                        else if ("-phi".equals(args[i])) {
                            if (MathUtils.isDouble(args[i+1])) {
                                phi = args[i+1];
                            }
                            else {
                                ChatUtils.replay(event, this.bot.format("chat.exception.illegalArg", this.bot.prefix + "help " + getName()));
                                return;
                            }
                        }
                    }
                }

                BufferedImage image = MinecraftUtils.playerModel(name, width, height, time, theta, phi);

                if (image != null) {
                    ChatUtils.replay(event, image, name);
                }
                else {
                    ChatUtils.replay(event, format("chat.noImage"));
                }
            }
            else {
                ChatUtils.replay(event, format("chat.wrongPlayer"));
            }
        });
    }

    @Override
    public boolean runnable(User user, int length) {
        return length >= 4 && length % 2 == 0;
    }
}
