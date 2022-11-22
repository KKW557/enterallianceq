package icu.kevin557.eq.utils;

import java.util.concurrent.*;

/**
 * @author 557
 */
public class Executor {

    private static final ExecutorService SERVICE = new ThreadPoolExecutor(3, 5, 1L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void run(Runnable runnable) {
        SERVICE.execute(runnable);
    }
}
