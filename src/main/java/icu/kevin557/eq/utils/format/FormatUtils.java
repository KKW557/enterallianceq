package icu.kevin557.eq.utils.format;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

/**
 * @author 557
 */
public class FormatUtils {

    private static final DecimalFormat FORMATTER = new DecimalFormat("#,##0.##");
    private static final Long TRILLIONS = 1000000000000L;
    private static final Long BILLION = 1000000000L;
    private static final Long MILLION = 1000000L;
    private static final Long KILO = 1000L;

    @NotNull
    public static String formatNumber(Number number) {
        return FORMATTER.format(number);
    }

    @NotNull
    public static String formatBigNumber(@NotNull Number number) {
        if (number.doubleValue() >= TRILLIONS) {
            return FORMATTER.format(number.doubleValue() / TRILLIONS) + "T";
        }
        else if (number.doubleValue() >= BILLION) {
            return FORMATTER.format(number.doubleValue() / BILLION) + "B";
        }
        else if (number.doubleValue() >= MILLION) {
            return FORMATTER.format(number.doubleValue() / MILLION) + "M";
        }
        else if (number.doubleValue() >= KILO) {
            return FORMATTER.format(number.doubleValue() / KILO) + "K";
        }
        else {
            return FORMATTER.format(number);
        }
    }

    @NotNull
    public static String formatRatioForNumber(@NotNull Number number1, @NotNull Number number2) {
        return formatNumber(number1.doubleValue() / (number2.doubleValue() == 0 ? 1 : number2.doubleValue()));
    }
}
