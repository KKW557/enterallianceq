package icu.kevin557.eq.utils.minecraft;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.utils.HttpUtils;
import icu.kevin557.eq.utils.Logger;
import net.mamoe.mirai.internal.deps.io.ktor.util.CaseInsensitiveMap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author 557
 */
public class MinecraftUtils {

    public static final Pattern UUID_PATTERN = Pattern.compile("[A-Za-z\\d]{8}-?[A-Za-z\\d]{4}-?[A-Za-z\\d]{4}-?[A-Za-z\\d]{4}-?[A-Za-z\\d]{12}");
    public static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z0-9_]+");

    public static final String UUID_URL = "https://api.mojang.com/users/profiles/minecraft/";
    public static final String PROFILE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";
    public static final Map<String, String> UUID_CACHE = new CaseInsensitiveMap<>();
    public static final Map<String, String> NAME_CACHE = new CaseInsensitiveMap<>();

    public static final String NAMEMC_URL = "https://namemc.com/profile/%s";
    public static final String SKINMODEL_URL = "https://s.namemc.com/3d/skin/body.png?width=%s&height=%s&model=%s&id=%s&cape=%s";

    public static String getName(String uuid) {
        uuid = shortUuid(uuid);
        if (NAME_CACHE.containsKey(uuid)) {
            return NAME_CACHE.get(uuid);
        }
        else {
            final String[] nu = {"", ""};
            try {
                JSONObject jsonObject = JSON.parseObject(HttpUtils.executeString(PROFILE_URL + uuid));
                nu[0] = jsonObject.getString("name");
                nu[1] = jsonObject.getString("id");
                UUID_CACHE.put(nu[0], nu[1]);
                NAME_CACHE.put(nu[1], nu[0]);
                return nu[0];
            } catch (IOException e) {
                Logger.info("Failed to get name for uuid '" + uuid + "'");
            } catch (NullPointerException ignored) {}
            return nu[0];
        }
    }

    public static String getUuid(String name) {
        if (UUID_CACHE.containsKey(name)) {
            return UUID_CACHE.get(name);
        }
        else {
            final String[] nu = {"", ""};
            try {
                JSONObject jsonObject = JSON.parseObject(HttpUtils.executeString(UUID_URL + name));
                nu[0] = jsonObject.getString("name");
                nu[1] = jsonObject.getString("id");
                UUID_CACHE.put(nu[0], nu[1]);
                NAME_CACHE.put(nu[1], nu[0]);
                return nu[1];
            } catch (IOException e) {
                Logger.info("Failed to get uuid for name '" + name + "'");
            } catch (NullPointerException ignored) {}
            return nu[1];
        }
    }

    @NotNull
    @Contract(pure = true)
    public static String shortUuid(@NotNull String uuid) {
        return uuid.replaceAll("-", "");
    }

    @NotNull
    public static String fullUuid(@NotNull String uuid) {
        return uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32);
    }

    public static boolean isNameOrUuid(String nu) {
        return UUID_PATTERN.matcher(nu).matches() || NAME_PATTERN.matcher(nu).matches();
    }

    public static String toUuid(String nu) {
        if (UUID_PATTERN.matcher(nu).matches()) {
            return shortUuid(nu);
        }
        else {
            return getUuid(nu);
        }
    }

    public static String toName(String nu) {
        if (NAME_PATTERN.matcher(nu).matches()) {
            return nu;
        }
        else {
            return getName(nu);
        }
    }

    @Nullable
    public static BufferedImage playerModel(String name, int width, int height, String time, String theta, String phi) {
        try {
            Document doc = Jsoup.parse(HttpUtils.executeString(String.format(NAMEMC_URL, name)));
            Element canvas = doc.body().selectFirst("main").selectFirst("div:nth-child(3)").selectFirst("div.col-md-auto.order-md-1").selectFirst("div:nth-child(1)").selectFirst("div").selectFirst("div").selectFirst("canvas");
            String model = canvas.attr("data-model");
            String id = canvas.attr("data-id");
            String cape = canvas.attr("data-cape");
            return ImageIO.read(HttpUtils.executeStream(skinModelUrl(width, height, model, id, cape, time, theta, phi)));
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

    private static String skinModelUrl(int width, int height, String model, String id, String cape, String time, String theta, String phi) {
        String url = String.format(SKINMODEL_URL, width, height, model, id, cape);
        if (time != null) {
            url += ("&time=" + time);
        }
        if (theta != null) {
            url += ("&theta=" + theta);
        }
        if (phi != null) {
            url += ("&phi=" + phi);
        }
        return url;
    }
}
