package icu.kevin557.eq.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.api.command.Logger;
import net.mamoe.mirai.internal.deps.io.ktor.util.CaseInsensitiveMap;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
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

    /**
     * 获取玩家名称
     * @param uuid 玩家uuid
     * @return 玩家名称
     */
    public static String getName(String uuid) {
        uuid = shortUuid(uuid);
        if (NAME_CACHE.containsKey(uuid)) {
            return NAME_CACHE.get(uuid);
        }
        else {
            final String[] nu = {"", ""};
            try {
                JSONObject body = JSON.parseObject(new URL(PROFILE_URL + uuid));
                nu[0] = body.getString("name");
                nu[1] = body.getString("id");
                UUID_CACHE.put(nu[0], nu[1]);
                NAME_CACHE.put(nu[1], nu[0]);
                return nu[0];
            } catch (MalformedURLException e) {
                Logger.info("Failed to get name for uuid '%s'", uuid);
            }
            return nu[0];
        }
    }

    /**
     * 获取玩家uuid
     * @param name 玩家名称
     * @return 玩家uuid
     */
    public static String getUuid(String name) {
        if (UUID_CACHE.containsKey(name)) {
            return UUID_CACHE.get(name);
        }
        else {
            final String[] nu = {"", ""};
            try {
                JSONObject body = JSON.parseObject(new URL(UUID_URL + name));
                nu[0] = body.getString("name");
                nu[1] = body.getString("id");
                UUID_CACHE.put(nu[0], nu[1]);
                NAME_CACHE.put(nu[1], nu[0]);
                return nu[1];
            } catch (MalformedURLException e) {
                Logger.info("Failed to get uuid for name '%s'", name);
            }
            return nu[1];
        }
    }

    /**
     * 去掉uuid中的-
     * @param uuid uuid
     * @return 短uuid
     */
    @NotNull
    public static String shortUuid(@NotNull String uuid) {
        return uuid.replaceAll("-", "");
    }

    /**
     * 将短uuid加上-
     * @param uuid 必须为短uuid
     * @return 完全uuid
     */
    @NotNull
    public static String fullUuid(@NotNull String uuid) {
        return uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32);
    }

    /**
     * 检查是否为名称或uuid
     * @param nu 可能的名称或uuid
     * @return 是否为名称或uuid
     */
    public static boolean isNameOrUuid(String nu) {
        return UUID_PATTERN.matcher(nu).matches() || NAME_PATTERN.matcher(nu).matches();
    }

    /**
     * 将玩家名称或uuid转换为短uuid
     * @see MinecraftUtils#shortUuid(String)
     * @param nu 名称或uuid
     * @return 短uuid
     * @see MinecraftUtils#getUuid(String)
     */
    public static String toUuid(String nu) {
        if (UUID_PATTERN.matcher(nu).matches()) {
            return shortUuid(nu);
        }
        else {
            return getUuid(nu);
        }
    }
}
