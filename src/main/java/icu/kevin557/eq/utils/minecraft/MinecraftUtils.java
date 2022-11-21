package icu.kevin557.eq.utils.minecraft;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import icu.kevin557.eq.api.command.Logger;
import icu.kevin557.eq.utils.FontUtils;
import icu.kevin557.eq.utils.HttpUtils;
import net.mamoe.mirai.internal.deps.io.ktor.util.CaseInsensitiveMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.AttributedString;
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
                JSONObject jsonObject = JSON.parseObject(HttpUtils.executeString(PROFILE_URL + uuid));
                nu[0] = jsonObject.getString("name");
                nu[1] = jsonObject.getString("id");
                UUID_CACHE.put(nu[0], nu[1]);
                NAME_CACHE.put(nu[1], nu[0]);
                return nu[0];
            } catch (IOException e) {
                Logger.info("Failed to get name for uuid '%s'", uuid);
            } catch (NullPointerException ignored) {}
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
                JSONObject jsonObject = JSON.parseObject(HttpUtils.executeString(UUID_URL + name));
                nu[0] = jsonObject.getString("name");
                nu[1] = jsonObject.getString("id");
                UUID_CACHE.put(nu[0], nu[1]);
                NAME_CACHE.put(nu[1], nu[0]);
                return nu[1];
            } catch (IOException e) {
                Logger.info("Failed to get uuid for name '%s'", name);
            } catch (NullPointerException ignored) {}
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


    /**
     * 添加转换为富文本
     * @param str 原文本
     * @param font 首选字体
     * @param fore 是否为前景色
     * @param alpha 透明度
     * @return 富文本
     */
    @NotNull
    public static AttributedString colorString(@NotNull String str, Font font, boolean fore, int alpha) {
        String formatString = str.replaceAll("\u00a7.?", "");
        AttributedString attributedString = FontUtils.fallbackFont(formatString, font);

        int strLength = str.length();
        Color color = Color.WHITE;
        int codeCount = 0;
        int start = 0;

        for (int end = 0; end < strLength; end++) {

            if (str.charAt(end) == '\u00a7' && end != strLength - 1) {
                if (color != null && start != end - codeCount) {
                    attributedString.addAttribute(TextAttribute.FOREGROUND, fore ? color.getForeColor(alpha) : color.getBackColor(alpha), start, end - codeCount);
                    start = end - codeCount;
                }
                color = Color.getColorByCode(str.charAt(end + 1));
                codeCount += 2;
                end += 1;
            }

        }

        if (color != null) {
            attributedString.addAttribute(TextAttribute.FOREGROUND, fore ? color.getForeColor(alpha) : color.getBackColor(alpha), start, formatString.length());
        }

        return attributedString;
    }

    /**
     * 添加转换为富文本
     * @param str 原文本
     * @param font 首选字体
     * @param fore 是否为前景色
     * @return 富文本
     */
    @NotNull
    public static AttributedString colorString(@NotNull String str, Font font, boolean fore) {
        return colorString(str, font, fore, 255);
    }

    /**
     * 获取一张渲染后的玩家模型 来自NameMC
     * @param name 玩家名称
     * @param width 图片宽度
     * @param height 图片高度
     * @return 渲染后的玩家模型
     */
    @Nullable
    public static BufferedImage playerModel(String name, int width, int height) {
        try {
            Document doc = Jsoup.parse(HttpUtils.executeString(String.format(NAMEMC_URL, name)));
            Element canvas = doc.body().selectFirst("main").selectFirst("div:nth-child(3)").selectFirst("div.col-md-auto.order-md-1").selectFirst("div:nth-child(1)").selectFirst("div").selectFirst("div").selectFirst("canvas");
            String model = canvas.attr("data-model");
            String id = canvas.attr("data-id");
            String cape = canvas.attr("data-cape");
            return ImageIO.read(HttpUtils.executeStream(skinModelUrl(width, height, model, id, cape, null, null, null)));
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }

    private static String skinModelUrl(int width, int height, String model, String id, String cape, String time, String theta, String phi) {
        String url = String.format(SKINMODEL_URL, width, height, model, id, cape);
        if (time != null) {
            url += ("&" + time);
        }
        if (theta != null) {
            url += ("&" + theta);
        }
        if (phi != null) {
            url += ("&" + phi);
        }
        return url;
    }

    /**
     * 原版颜色
     */
    public enum Color {
        /**
         * 黑色
         */
        BLACK(0, 0, '0'),
        /**
         * 深蓝色
         */
        DARK_BLUE(0x0000AA, 0x00002A, '1'),
        /**
         * 深绿色
         */
        DARK_GREEN(0x00AA00, 0x002A00, '2'),
        /**
         * 湖蓝色
         */
        DARK_AQUA(0x00AAAA, 0x002A2A, '3'),
        /**
         * 深红色
         */
        DARK_RED(0xAA0000, 0x2A0000, '4'),
        /**
         * 紫色
         */
        DARK_PURPLE(0xAA00AA, 0x2A002A, '5'),
        /**
         * 金色
         */
        GOLD(0xFFAA00, 0x402A00, '6'),
        /**
         * 灰色
         */
        GRAY(0xAAAAAA, 0x2A2A2A, '7'),
        /**
         * 深灰色
         */
        DARK_GRAY(0x555555, 0x151515, '8'),
        /**
         * 蓝色
         */
        BLUE(0x5555FF, 0x15153F, '9'),
        /**
         * 绿色
         */
        GREEN(0x55FF55, 0x153F15, 'a'),
        /**
         * 深红色
         */
        AQUA(0x55FFFF, 0x153F3F, 'b'),
        /**
         * 红色
         */
        RED(0xFF5555, 0x3F1515, 'c'),
        /**
         * 粉色
         */
        LIGHT_PURPLE(0xFF55FF, 0x3F153F, 'd'),
        /**
         * 黄色
         */
        YELLOW(0xFFFF55, 0x3F3F15, 'e'),
        /**
         * 白色
         */
        WHITE(0xFFFFFF, 0x3F3F3F, 'f'),
        /**
         * 硬币金
         */
        MINECOIN_GOLD(0xDDD605, 0x373501, 'g');

        /**
         * 前景色
         */
        private final java.awt.Color foreColor;

        /**
         * 背景色
         */
        private final java.awt.Color backColor;

        /**
         * 代码
         */
        private final char code;


        /**
         * @param foreColor 前景色
         * @param backColor 背景色
         */
        Color(int foreColor, int backColor, char code) {
            this.foreColor = new java.awt.Color(foreColor);
            this.backColor = new java.awt.Color(backColor);
            this.code = code;
        }

        @Override
        public String toString() {
            return "\u00a7" + code;
        }

        //region getters

        public java.awt.Color getForeColor() {
            return foreColor;
        }

        public java.awt.Color getBackColor() {
            return backColor;
        }

        public java.awt.Color getForeColor(int alpha) {
            return new java.awt.Color(foreColor.getRed(), foreColor.getGreen(), foreColor.getBlue(), alpha);
        }

        public java.awt.Color getBackColor(int alpha) {
            return new java.awt.Color(backColor.getRed(), backColor.getGreen(), backColor.getBlue(), alpha);
        }

        public char getCode() {
            return code;
        }

        //endregion

        /**
         * 获取Minecraft颜色
         * @param code 代码
         * @return Minecraft颜色
         */
        @Nullable
        public static Color getColorByCode(char code) {
            for (Color color : Color.values()) {
                if (color.getCode() == code) {
                    return color;
                }
            }
            return null;
        }

        /**
         * 获取Minecraft颜色
         * @param name 颜色名称
         * @return Minecraft颜色
         */
        public static Color getColor(String name) {
            for (Color color : Color.values()) {
                if (color.name().equals(name)) {
                    return color;
                }
            }
            return WHITE;
        }
    }
}
