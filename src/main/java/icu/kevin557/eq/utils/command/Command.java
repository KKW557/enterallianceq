package icu.kevin557.eq.utils.command;

import icu.kevin557.eq.utils.bot.EqBot;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.MathUtils;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 557
 */
public abstract class Command {

    // region defaults

    /**
     * 默认命令前缀
     */
    public static final String PREFIX_DEFAULT = ".";

    // endregion

    /**
     * 命令所属的 EQBot
     */
    protected final EqBot bot;

    /**
     *
     * @param bot 命令所属的 EQBot
     * @since A1
     */
    public Command(EqBot bot) {
        this.bot = bot;
    }

    /**
     * 运行命令
     * @param event 消息事件
     * @param args 命令参数
     * @since A1
     */
    public abstract void execute(MessageEvent event, String[] args);

    /**
     * 获取命令的功能介绍
     * @return 命令介绍
     */
    public abstract String description();

    /**
     * 获取命令用法
     * 规范: &lt;arg&gt;为必选参数, [arg]为可选参数, 多个参数之间用'|'隔开, 如: [arg1|arg2]
     * @return 命令用法
     */
    public abstract String[] usages();

    public static class Help extends Command {

        /**
         * 每页显示的命令数
         */
        private static final int COMMAND_PER_PAGE = 5;

        /**
         * @param bot 命令所属的 EQBot
         * @since A1
         */
        public Help(EqBot bot) {
            super(bot);
        }

        @Override
        public void execute(MessageEvent event, String[] args) {

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
                    ChatUtils.replay(event, "此页码不存在！");
                    return;
                }

                MessageChainBuilder messageBuilder = new MessageChainBuilder();
                messageBuilder.append(String.format("可使用的命令(%d) 第%d页 共%d页:", commandList.size(), page, pages));

                int start = (page - 1) * COMMAND_PER_PAGE;
                int end = Math.min(page * COMMAND_PER_PAGE, commandList.size());

                for (int i = start; i < end; i++) {
                    Command command = commandList.get(i);
                    messageBuilder.append("\n\n").append(command.description()).append(": ");

                    for (String usage : command.usages()) {
                        messageBuilder.append('\n').append(this.bot.getPrefix()).append(usage);
                    }
                }

                ChatUtils.replay(event, messageBuilder.toString());
            }
            else {
                Command command = this.bot.getCommands().get(arg);

                if (command == null) {
                    ChatUtils.replay(event, "此命令不存在！");
                    return;
                }

                MessageChainBuilder messageBuilder = new MessageChainBuilder();
                messageBuilder.append("命令介绍: ").append(command.description()).append('\n');

                List<String> nameList = new ArrayList<>();

                for (Map.Entry<String, Command> entry : this.bot.getCommands().entrySet()) {
                    if (command == entry.getValue()) {
                        nameList.add(entry.getKey());
                    }
                }

                messageBuilder.append("\n命令用法: ").append(command.description()).append('\n');
                for (String usage : command.usages()) {
                    messageBuilder.append('\n').append(this.bot.getPrefix()).append(usage);
                }

                messageBuilder.append("\n命令名称: \n");
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
        public String description() {
            return "查询可使用的命令";
        }

        @Override
        public String[] usages() {
            return new String[] {"帮助 [页码|命令]"};
        }
    }
}
