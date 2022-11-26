package icu.kevin557.eq.bot.minecraft.command;

import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.command.Command;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.Executor;
import icu.kevin557.eq.utils.math.MathUtils;
import icu.kevin557.eq.utils.games.minecraft.MinecraftUtils;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;

import java.awt.image.BufferedImage;

/**
 * @author 557
 */
public class ModelCommand extends Command {

    public ModelCommand(EnterallianceQ bot) {
        super(bot, "model");
    }

    @Override
    public void execute(MessageEvent event, String[] args) {
        ChatUtils.replay(event, format("chat.waitFor", args[1]));
        Executor.run(() -> {
            if (MinecraftUtils.isNameOrUuid(args[1])) {
                String name = MinecraftUtils.toName(args[1]);

                String width = null;
                String height = null;
                String time = null;
                String theta = null;
                String phi = null;

                for (int i = 4; i < args.length; i++) {
                    if (i % 2 == 0) {
                        if ("-width".equals(args[i])) {
                            if (MathUtils.isDouble(args[i+1])) {
                                width = args[i+1];
                            }
                            else {
                                ChatUtils.replay(event, this.bot.format("chat.exception.illegalArg", this.bot.prefix + "help " + getName()));
                                return;
                            }
                        }
                        else if ("-height".equals(args[i])) {
                            if (MathUtils.isDouble(args[i+1])) {
                                height = args[i+1];
                            }
                            else {
                                ChatUtils.replay(event, this.bot.format("chat.exception.illegalArg", this.bot.prefix + "help " + getName()));
                                return;
                            }
                        }
                        else if ("-time".equals(args[i])) {
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
                    ChatUtils.replay(event, image, "player_model");
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
        return length >= 2 && length % 2 == 0;
    }
}
