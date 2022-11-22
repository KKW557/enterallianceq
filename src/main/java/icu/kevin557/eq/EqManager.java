package icu.kevin557.eq;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.bot.manager.ManagerBot;
import icu.kevin557.eq.bot.minecraft.MinecraftBot;
import icu.kevin557.eq.utils.Logger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 557
 */
public class EqManager {

    private static final EnteralianceQ[] REGISTER_BOTS = {new ManagerBot(), new MinecraftBot()};
    public static final List<EnteralianceQ> BOTS = new ArrayList<>();

    public static void loadBots() {
        File file = new File("bots.json");
        assert file.exists();
        try {
            JSONArray jsonArray = JSON.parseArray(FileUtils.readFileToString(file, "UTF-8"));
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                long qq = jsonObject.getLong("qq");
                byte[] password = jsonObject.getJSONArray("password").to(byte[].class);
                for (EnteralianceQ bot : REGISTER_BOTS) {
                    if (bot.getName().equals(name)) {
                        try {
                            BOTS.add(bot.newInstance(qq, password));
                            Logger.info(String.format("Loaded %s bot(%d).", name, qq));
                        } catch (InstantiationException | IllegalAccessException e) {
                            Logger.info(String.format("Failed to load %s bot(%d).", name, qq));
                        }
                    }
                }
            }

        } catch (IOException e) {
            Logger.info("Failed to load bots.");
        }

    }

    public static void loadBotConfigs() {
        for (EnteralianceQ bot : BOTS) {
            bot.loadConfigs();
        }
    }

    public static void registerBotCommands() {
        for (EnteralianceQ bot : BOTS) {
            bot.registerCommands();
        }
    }

    public static void loginBots() {
        for (EnteralianceQ bot : BOTS) {
            bot.login();
        }
    }

    public static void subscribeBotEvents() {
        for (EnteralianceQ bot : BOTS) {
            bot.subscribeEvents();
        }
    }
}
