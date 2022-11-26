package icu.kevin557.eq.bot.clashroyale.image;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.EnterallianceQ;
import icu.kevin557.eq.image.AbstractImage;
import icu.kevin557.eq.utils.font.FontUtils;
import icu.kevin557.eq.utils.format.FormatUtils;
import icu.kevin557.eq.utils.games.clashroyale.ClashroyaleChest;
import icu.kevin557.eq.utils.render.ImageUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author 557
 */
public class ChestImage extends AbstractImage {

    private final JSONArray chests;

    public ChestImage(EnterallianceQ bot, @NotNull JSONArray chests) {
        super(bot, 592, (int) (16 + Math.ceil(chests.size() / 4D) * 184));
        this.chests = chests;
    }

    @Override
    public BufferedImage get() {

        ImageUtils.fillColor(image, Color.BLACK);

        int x = 16;
        int y = 16;
        int count = 0;

        for (int i = 0; i < chests.size(); i++) {
            count += 1;

            JSONObject chestObject = chests.getJSONObject(i);
            ClashroyaleChest chest = ClashroyaleChest.fromName(chestObject.getString("name"));

            String index = "+" + FormatUtils.formatNumber(chestObject.getInteger("index") + 1);

            ImageUtils.drawImage(image, chest.getImage(), x, y);

            ImageUtils.drawCenterString(image, index, x + 64, y + 152, FontUtils.CLASHROYALE_TITLE24, Color.WHITE);

            if (count == 4) {
                x = 16;
                y += 184;
                count = 0;
            }
            else {
                x += 144;
            }
        }

        return image;
    }
}
