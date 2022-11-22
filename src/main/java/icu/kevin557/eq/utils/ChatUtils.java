package icu.kevin557.eq.utils;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author 557
 */
public class ChatUtils {

    public static void replay(@NotNull MessageEvent event, String message) {
        event.getSubject().sendMessage(new MessageChainBuilder().append(new QuoteReply(event.getMessage())).append(message).build());
    }

    public static void replay(@NotNull MessageEvent event, MessageChain message) {
        event.getSubject().sendMessage(new MessageChainBuilder().append(new QuoteReply(event.getMessage())).append(message).build());
    }

    public static void replay(@NotNull MessageEvent event, @NotNull BufferedImage image, String fileName) {
        try {
            File file = new File(event.getBot().getConfiguration().getCacheDir(), fileName + ".png");
            ImageIO.write(image, "png", file);
            event.getSubject().sendMessage(new MessageChainBuilder().append(new QuoteReply(event.getMessage())).append(Contact.uploadImage(event.getSubject(), file)).build());
        } catch (IOException e) {
            event.getBot().getLogger().info("Failed to reply a image.");
        }
    }
}
