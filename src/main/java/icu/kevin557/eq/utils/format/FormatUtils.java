package icu.kevin557.eq.utils.format;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

/**
 * @author 557
 */
public class FormatUtils {

    private static final DecimalFormat FORMATTER = new DecimalFormat("#,##0.##");

    @NotNull
    public static String formatNumber(Number number) {
        return FORMATTER.format(number);
    }
}
