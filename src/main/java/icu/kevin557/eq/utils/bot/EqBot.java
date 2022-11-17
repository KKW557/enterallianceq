package icu.kevin557.eq.utils.bot;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.Main;
import icu.kevin557.eq.bots.clashroyale.ClashroyaleBot;
import icu.kevin557.eq.bots.manager.ManagerBot;
import icu.kevin557.eq.bots.minecraft.MinecraftBot;
import icu.kevin557.eq.utils.command.Command;
import icu.kevin557.eq.utils.command.Logger;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
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
 * */
public class EqBot {

    /**
     * mirai 实例
     * */
    protected Bot bot;

    /**
     * mirai 配置
     */
    protected BotConfiguration configuration;

    /**
     * EqBot QQ号码
     * */
    protected long qq;

    /**
     * EqBot QQ密码MD5
     * @see icu.kevin557.eq.utils.CryptUtils#string2Md5(String)
     * */
    protected byte[] password;

    /**
     * EqBot 命令前缀
     */
    protected String prefix;

    /**
     * EqBot 命令集
     */
    protected Map<String, Command> commands;

    /**
     * 构造方法
     * @param qq QQ号码
     * @param password QQ密码MD5
     * @param name 名称
     * @param prefix 命令前缀
     * @since A1
     * @return EqBot 实例
     */
    public EqBot newInstance(long qq, byte[] password, String prefix) {
        EqBot bot = new EqBot();
        bot.configuration = newConfiguration(qq);
        bot.qq = qq;
        bot.password = password;
        bot.prefix = prefix;
        bot.commands = new LinkedHashMap<>();
        return bot;
    }

    /**
     * 运行
     */
    public void run() {
        this.init();
        this.login();
        this.afterLogin();
    }

    /**
     * 初始化 EqBot
     */
    protected void init() {
        this.registerCommand(new Command.Help(this), "帮助", "help", "h");
    }

    /**
     * 登录 mirai
     */
    protected void login() {
        this.bot = BotFactory.INSTANCE.newBot(qq, password, configuration);
        this.bot.login();
    }

    /**
     * 登录后操作
     */
    protected void afterLogin() {
        this.bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, (event -> {
            String message = event.getMessage().contentToString();

            if (message.startsWith(this.prefix) && message.length() > this.prefix.length()) {

                /* 命令参数 */
                String[] args = message.substring(1).split(" ");

                /* 命令对象 */
                Command command = commands.get(args[0]);

                /* 不为 null, 直接运行 */
                if (command != null) {
                    this.bot.getLogger().info(String.format("[%s(%d)] %s(%d) CMD: %s", event.getGroup().getName(), event.getGroup().getId(), event.getSender().getNick(), event.getSender().getId(), message));
                    command.execute(event, args);
                }
            }
        }));
    }

    /**
     * 注册一个命令
     * @param command 命令对象
     * @param names 命令名称
     */
    public void registerCommand(Command command, @NotNull String... names) {
        for (String name : names)
        {
            this.commands.put(name, command);
        }
    }

    /**
     * 关闭 mirai 实例
     */
    public void logout() {
        this.bot.close();
    }

    /**
     * 构建一个mirai配置, 协议类型为 MacOS
     * @param qq QQ号码
     * @return mirai配置
     * @author 557
     */
    @NotNull
    private static BotConfiguration newConfiguration(long qq) {
        BotConfiguration configuration = new BotConfiguration();
        File botDir = new File(Main.QQ_DIR, qq + "");
        botDir.mkdir();
        configuration.fileBasedDeviceInfo(new File(botDir, "devices.json").getPath());
        configuration.setCacheDir(new File(botDir, "cache"));
        configuration.setProtocol(BotConfiguration.MiraiProtocol.MACOS);
        return configuration;
    }

    /**
     * 继承类必须重写此方法！！！
     * @return EqBot 标识名称
     * @since A1
     */
    public String getName() {
        return null;
    }

    //region getters

    public Bot getBot() {
        return bot;
    }

    public BotConfiguration getConfiguration() {
        return configuration;
    }

    public long getQq() {
        return qq;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getPrefix() {
        return prefix;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    //endregion

    /**
     * EqBot 管理类
     * @author 557
     */
    public static class Manager {

        /**
         * EqBot 实例列表
         */
        private static final List<EqBot> BOTS = new ArrayList<>();

        /**
         * EqBot 注册表
         */
        private static final EqBot[] REGISTER_BOTS = {new ManagerBot(), new MinecraftBot(), new ClashroyaleBot()};

        /**
         * 从配置中加载所有 EqBot
         */
        public static void loadBots() throws IOException {
            File botsFile = new File(Main.FILES_DIR, "bots.json");
            if (botsFile.exists()) {
                JSONArray botArray = JSON.parseArray(FileUtils.readFileToString(botsFile, "UTF-8"));
                for (int i = 0; i < botArray.size(); i++) {
                    JSONObject botObject = botArray.getJSONObject(i);
                    String botName = botObject.getString("name");
                    long qq = botObject.getLong("qq");
                    JSONArray passwordArray = botObject.getJSONArray("password");
                    byte[] password = new byte[passwordArray.size()];
                    for (int j = 0; j < passwordArray.size(); j++) {
                        password[j] = passwordArray.getByte(j);
                    }
                    String prefix = botObject.getString("prefix");
                    for (EqBot registerBot : REGISTER_BOTS) {
                        if (registerBot.getName().equals(botName)) {
                            BOTS.add(registerBot.newInstance(qq, password, prefix));
                            Logger.info(String.format("Add %s bot(%d).", botName, qq));
                        }
                        else {
                            Logger.info(String.format("Unknown bot '%s'", botName));
                        }
                    }
                }
            }
        }


        /**
         * 运行全部 EqBot
         */
        public static void runBots() {
            for (EqBot bot : BOTS) {
                bot.run();
            }
        }

        /**
         * 登出全部 EqBot
         */
        public static void logoutBots() {
            for (EqBot bot : BOTS) {
                bot.logout();
            }
        }
    }
}
