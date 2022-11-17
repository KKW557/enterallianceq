package icu.kevin557.eq.utils;

import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import org.jetbrains.annotations.NotNull;

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
}
