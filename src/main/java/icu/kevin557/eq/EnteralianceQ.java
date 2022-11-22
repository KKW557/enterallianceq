package icu.kevin557.eq;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.command.Command;
import icu.kevin557.eq.command.HelpCommand;
import icu.kevin557.eq.utils.ChatUtils;
import icu.kevin557.eq.utils.resouces.ConfigUtils;
import icu.kevin557.eq.utils.resouces.I18n;
import icu.kevin557.eq.utils.Logger;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 557
 */
public abstract class EnteralianceQ {

    private Bot bot;
    protected BotConfiguration configuration;
    protected long qq;
    protected byte[] password;

    protected Map<String, Command> commands;

    public List<Long> admins = new ArrayList<>();
    public String prefix = ".";
    public String language = "zh_cn";

    public void loadConfigs() {
        File file = new File(dir(), "config.json");
        if (file.exists()) {
            try {
                JSONObject jsonObject = JSON.parseObject(FileUtils.readFileToString(file, "UTF-8"));

                if (jsonObject.getJSONArray("admins") != null) {
                    this.admins = jsonObject.getJSONArray("admins").toJavaList(Long.class);
                }

                if (jsonObject.get("prefix") != null) {
                    this.prefix = jsonObject.getString("prefix");
                }

                if (jsonObject.get("language") != null) {
                    this.language = jsonObject.getString("language");
                }

            } catch (IOException e) {
                Logger.info("Failed to load config.");
            }
        }
    }

    public void registerCommand(Command command, @NotNull String... alias) {
        for (String alia : alias)
        {
            this.commands.put(alia, command);
        }
    }

    public void login() {
        if (this.bot == null || !this.bot.isOnline()) {
            this.bot = BotFactory.INSTANCE.newBot(qq, password, configuration);
            this.bot.login();
        }
    }

    public void registerCommands() {
        this.registerCommand(new HelpCommand(this), "help", "h");
    }

    public void subscribeEvents() {
        this.bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, (event -> {
            String message = event.getMessage().contentToString();

            if (message.startsWith(this.prefix) && message.length() > this.prefix.length()) {

                String[] args = message.substring(1).split(" ");

                Command command = commands.get(args[0]);

                if (command != null) {
                    if (command.runnable(event.getSender(), args.length)) {
                        command.execute(event, args);
                        this.bot.getLogger().info(String.format("[%s(%d)] %s(%d) CMD: %s", event.getGroup().getName(), event.getGroup().getId(), event.getSender().getNick(), event.getSender().getId(), message));
                    }
                    else {
                        ChatUtils.replay(event, format("chat.exception.illegalArg", this.prefix + "help " + command.getName()));
                    }
                }
            }
        }));
    }

    public void logout() {
        if (this.bot.isOnline()) {
            this.bot.close();
        }
    }

    public boolean isAdmin(@NotNull User user) {
        return admins.contains(user.getId());
    }

    public String format(String translateKey, Object... params) {
        return I18n.format(language, translateKey, params);
    }

    public File dir() {
        return this.configuration.getWorkingDir();
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public EnteralianceQ newInstance(long qq, byte[] password) throws InstantiationException, IllegalAccessException {
        EnteralianceQ bot = getClass().newInstance();
        bot.configuration = configuration(qq);
        bot.qq = qq;
        bot.password = password;
        bot.commands = new LinkedHashMap<>();
        return bot;
    }

    public abstract String getName();

    @NotNull
    private static BotConfiguration configuration(long qq) {
        BotConfiguration configuration = new BotConfiguration();
        File file = new File(ConfigUtils.DIR_QQ, qq + "");
        file.mkdir();
        configuration.setWorkingDir(file);
        configuration.fileBasedDeviceInfo("devices.json");
        configuration.setProtocol(BotConfiguration.MiraiProtocol.MACOS);
        return configuration;
    }
}
