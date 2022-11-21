package icu.kevin557.eq.utils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author 557
 */
public class HttpUtils {

    private static final OkHttpClient CLIENT = new OkHttpClient();

    @NotNull
    public static String executeString(String url) throws IOException {
        final Request request = new Request.Builder().url(url).build();
        Call call = CLIENT.newCall(request);
        return Objects.requireNonNull(call.execute().body()).string();
    }

    @NotNull
    public static InputStream executeStream(String url) throws IOException {
        final Request request = new Request.Builder().url(url).build();
        Call call = CLIENT.newCall(request);
        return Objects.requireNonNull(call.execute().body()).byteStream();
    }
}
