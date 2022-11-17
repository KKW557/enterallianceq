package icu.kevin557.eq.utils.bot;

import icu.kevin557.eq.Main;
import icu.kevin557.eq.utils.command.Command;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 557
 * */
public abstract class EqBot {

    /**
     * mirai 实例
     * */
    protected Bot bot;

    /**
     * mirai 配置
     */
    protected final BotConfiguration configuration;

    /**
     * EqBot QQ号码
     * */
    protected final long qq;

    /**
     * EqBot QQ密码MD5
     * @see icu.kevin557.eq.utils.CryptUtils#string2Md5(String)
     * */
    protected final byte[] password;

    /**
     * EqBot 名称
     * */
    protected final String name;

    /**
     * EqBot 命令前缀
     */
    protected final String prefix;

    /**
     * EqBot 命令集
     */
    protected final Map<String, Command> commands;

    /**
     *
     * @param qq QQ号码
     * @param password QQ密码MD5
     * @param name 名称
     * @param prefix 命令前缀
     * @since A1
     */
    public EqBot(long qq, byte[] password, String name, String prefix) {
        this.configuration = newConfiguration(qq);
        this.qq = qq;
        this.password = password;
        this.name = name;
        this.prefix = prefix;
        this.commands = new LinkedHashMap<>();
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
        this.registerCommand(new Command.Help(this), "帮助", "help");
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
     * 构建一个mirai配置, 协议类型为 MacOS
     * @param qq QQ号码
     * @return mirai配置
     * @author 557
     */
    @NotNull
    private static BotConfiguration newConfiguration(long qq) {
        BotConfiguration configuration = new BotConfiguration();
        File botDir = new File(Main.FILES_DIR, qq + "");
        botDir.mkdir();
        configuration.fileBasedDeviceInfo(new File(botDir, "devices.json").getPath());
        configuration.setCacheDir(new File(botDir, "cache"));
        configuration.setProtocol(BotConfiguration.MiraiProtocol.MACOS);
        return configuration;
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

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    //endregion
}
