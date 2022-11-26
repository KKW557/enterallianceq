package icu.kevin557.eq.bot.clashroyale;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.bot.clashroyale.command.ChestCommand;
import icu.kevin557.eq.utils.Logger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author 557
 */
public class ClashroyaleBot extends EnterallianceQ {

    public String token = "";

    @Override
    public void registerCommands() {
        super.registerCommands();

        this.registerCommand(new ChestCommand(this), "chest", "cs", "宝箱");
    }

    @Override
    public void loadConfigs() {
        super.loadConfigs();

        File file = new File(dir(), "CR.json");

        if (file.exists()) {
            try {
                JSONObject jsonObject = JSON.parseObject(FileUtils.readFileToString(file, "UTF-8"));

                if (jsonObject.get("token") != null) {
                    this.token = jsonObject.getString("token");
                }

            } catch (IOException e) {
                Logger.info("Failed to load config.");
            }
        }

    }

    @Override
    public String getName() {
        return "CR";
    }
}
