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

    /**
     * 回复一条消息
     * @param event 消息事件
     * @param message 要发送的消息
     */
    public static void replay(@NotNull MessageEvent event, String message) {
        event.getSubject().sendMessage(new MessageChainBuilder().append(new QuoteReply(event.getMessage())).append(message).build());
    }

    /**
     * 回复一条消息
     * @param event 消息事件
     * @param message 要发送的消息
     */
    public static void replay(@NotNull MessageEvent event, MessageChain message) {
        event.getSubject().sendMessage(new MessageChainBuilder().append(new QuoteReply(event.getMessage())).append(message).build());
    }

    /**
     * 回复一张图片
     * @param event 消息事件
     * @param image 图片
     * @param fileName 保存的文件名称
     */
    public static void replay(@NotNull MessageEvent event, @NotNull BufferedImage image, String fileName) {
        try {
            File file = new File(event.getBot().getConfiguration().getCacheDir(), fileName + ".png");
            ImageIO.write(image, "png", file);
            event.getSubject().sendMessage(new MessageChainBuilder().append(new QuoteReply(event.getMessage())).append(Contact.uploadImage(event.getSubject(), file)).build());
        } catch (IOException e) {
            event.getBot().getLogger().info("Reply image was failed!");
        }
    }
}
