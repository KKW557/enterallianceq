package icu.kevin557.eq.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

/**
 * @author 557
 */
public class RandomUtils {

    private static final Random RANDOM = new Random();

    @Nullable
    public static <T> T random(@NotNull List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(RANDOM.nextInt(list.size()));
    }
}
