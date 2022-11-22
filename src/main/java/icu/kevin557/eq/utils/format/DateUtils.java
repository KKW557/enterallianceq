package icu.kevin557.eq.utils.format;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author 557
 */
public class DateUtils {

    @NotNull
    public static String formatChina(long date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(date);
    }
}
