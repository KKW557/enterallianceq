package icu.kevin557.eq.utils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author 557
 */
public class HttpUtils {

    private static final OkHttpClient CLIENT = new OkHttpClient();

    @NotNull
    public static String getString(Request request) throws IOException {
        Call call = CLIENT.newCall(request);
        return Objects.requireNonNull(call.execute().body()).string();
    }

    @NotNull
    public static String getString(String url) throws IOException {
        return getString(new Request.Builder().url(url).build());
    }

    @NotNull
    public static InputStream getStream(Request request) throws IOException {
        Call call = CLIENT.newCall(request);
        return Objects.requireNonNull(call.execute().body()).byteStream();
    }

    @NotNull
    public static InputStream getStream(String url) throws IOException {
        return getStream(new Request.Builder().url(url).build());
    }

    @Nullable
    public static BufferedImage getImage(String url) {
        try {
            return ImageIO.read(getStream(url));
        } catch (IOException e) {
            return null;
        }
    }
}
