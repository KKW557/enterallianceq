package icu.kevin557.eq.utils;

import java.io.File;

/**
 * @author 557
 */
public class ConfigUtils {

    public static File DIR_LANG = new File("lang");
    public static File DIR_QQ = new File("qq");

    public static void mkdirs() {
        DIR_LANG.mkdir();
        DIR_QQ.mkdir();
    }
}
