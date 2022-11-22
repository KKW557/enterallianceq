package icu.kevin557.eq.utils.resouces;

import java.io.File;

/**
 * @author 557
 */
public class ConfigUtils {

    public static File DIR_FONT = new File("font");
    public static File DIR_IMAGE = new File("image");
    public static File DIR_IMAGE_CACHE = new File(DIR_IMAGE, "cache");
    public static File DIR_LANG = new File("lang");
    public static File DIR_QQ = new File("qq");

    public static void mkdirs() {
        DIR_FONT.mkdir();
        DIR_IMAGE.mkdir();
        DIR_IMAGE_CACHE.mkdir();
        DIR_LANG.mkdir();
        DIR_QQ.mkdir();
    }
}
