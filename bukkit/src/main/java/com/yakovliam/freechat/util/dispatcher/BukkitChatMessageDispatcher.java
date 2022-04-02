package com.yakovliam.freechat.util.dispatcher;

import com.yakovliam.freechat.api.model.message.ChatMessage;
import com.yakovliam.freechat.api.util.dispatcher.ChatMessageDispatcher;
import com.yakovliam.freechat.channel.ChatChannel;
import com.yakovliam.freechat.user.ChatUser;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;

public class BukkitChatMessageDispatcher implements ChatMessageDispatcher<ChatUser, ChatChannel> {

    /**
     * Bukkit audiences
     */
    private final BukkitAudiences bukkitAudiences;

    /**
     * Bukkit chat message dispatcher
     *
     * @param bukkitAudiences bukkit audiences
     */
    public BukkitChatMessageDispatcher(BukkitAudiences bukkitAudiences) {
        this.bukkitAudiences = bukkitAudiences;
    }

    @Override
    public void dispatch(ChatMessage<ChatUser, ChatChannel> chatMessage) {
        // todo implement networking dispatching if applicable (by config context, maybe?)

        chatMessage.targets().forEach(channel -> {
            // todo implement canSee/canUse for players
            if (channel.isGlobal()) {
                Bukkit.getOnlinePlayers().forEach(receiver -> {
                    bukkitAudiences.player(receiver).sendMessage(chatMessage.content());
                });
            }
        });
    }
}
