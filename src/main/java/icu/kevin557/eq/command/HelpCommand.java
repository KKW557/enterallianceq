package icu.kevin557.eq.command;

import icu.kevin557.eq.EnteralianceQ;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.math.MathUtils;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 557
 */
public class HelpCommand extends Command {

    private static final int COMMAND_PER_PAGE = 5;

    public HelpCommand(EnteralianceQ bot) {
        super(bot, "help");
    }

    @Override
    public void execute(MessageEvent event, @NotNull String[] args) {

        String arg = args.length == 2 ? args[1] : "1";

        if (MathUtils.isInteger(arg)) {
            List<Command> commandList = new ArrayList<>();
            for (Map.Entry<String, Command> entry : this.bot.getCommands().entrySet()) {
                if (!commandList.contains(entry.getValue())) {
                    commandList.add(entry.getValue());
                }
            }

            int page = Integer.parseInt(arg);
            int pages = Math.max((int) Math.ceil(commandList.size() / (double)COMMAND_PER_PAGE), 1);

            if (page > pages || pages < 1) {
                ChatUtils.replay(event, format("chat.invalidPage"));
                return;
            }

            MessageChainBuilder messageBuilder = new MessageChainBuilder();
            messageBuilder.append(format("chat.availableCommands", commandList.size(), page, pages));

            int start = (page - 1) * COMMAND_PER_PAGE;
            int end = Math.min(page * COMMAND_PER_PAGE, commandList.size());

            for (int i = start; i < end; i++) {
                Command command = commandList.get(i);
                messageBuilder.append("\n\n").append(command.description()).append(": ");

                messageBuilder.append('\n').append(String.format(command.usage(), this.bot.prefix));

            }

            ChatUtils.replay(event, messageBuilder.build());
        }
        else {
            Command command = this.bot.getCommands().get(arg);

            if (command == null) {
                ChatUtils.replay(event, format("chat.unknownCommand"));
                return;
            }

            MessageChainBuilder messageBuilder = new MessageChainBuilder();
            messageBuilder.append(format("chat.commandDescription")).append('\n').append(command.description()).append('\n');

            List<String> nameList = new ArrayList<>();

            for (Map.Entry<String, Command> entry : this.bot.getCommands().entrySet()) {
                if (command == entry.getValue()) {
                    nameList.add(entry.getKey());
                }
            }

            messageBuilder.append('\n').append(format("chat.commandUsage"));
            messageBuilder.append('\n').append(String.format(command.usage(), this.bot.prefix));

            messageBuilder.append("\n\n").append(format("chat.commandAlias")).append('\n');
            for (int i = 0; i < nameList.size(); i++) {
                messageBuilder.append(nameList.get(i));
                if (i != nameList.size() - 1) {
                    messageBuilder.append(", ");
                }
            }

            ChatUtils.replay(event, messageBuilder.build());
        }
    }

    @Override
    public boolean runnable(User user, int length) {
        return length <= 2;
    }
}
