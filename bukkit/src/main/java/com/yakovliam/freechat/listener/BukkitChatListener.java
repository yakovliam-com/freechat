package com.yakovliam.freechat.listener;

import com.yakovliam.freechat.api.listener.ChatListener;
import com.yakovliam.freechat.api.model.message.ChatMessage;
import com.yakovliam.freechat.api.user.UsersHolder;
import com.yakovliam.freechat.channel.ChatChannel;
import com.yakovliam.freechat.user.ChatUser;
import com.yakovliam.freechat.util.BukkitChatMessageProcessor;
import com.yakovliam.freechat.util.dispatcher.BukkitChatMessageDispatcher;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class BukkitChatListener extends ChatListener<Player> implements Listener {

    /**
     * Users holder
     */
    private final UsersHolder<ChatUser> usersHolder;

    /**
     * Chat message processor
     */
    private final BukkitChatMessageProcessor bukkitChatMessageProcessor;

    /**
     * Chat message dispatcher
     */
    private final BukkitChatMessageDispatcher chatMessageDispatcher;

    /**
     * Bukkit chat listener
     *
     * @param usersHolder                users holder
     * @param bukkitChatMessageProcessor chat message processor
     * @param chatMessageDispatcher      chat message dispatcher
     */
    public BukkitChatListener(
            UsersHolder<ChatUser> usersHolder,
            BukkitChatMessageProcessor bukkitChatMessageProcessor,
            BukkitChatMessageDispatcher chatMessageDispatcher
    ) {
        this.usersHolder = usersHolder;
        this.bukkitChatMessageProcessor = bukkitChatMessageProcessor;
        this.chatMessageDispatcher = chatMessageDispatcher;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        // get content
        String content = event.getMessage();

        // cancel event
        event.setCancelled(true);

        // handle chat event
        this.handleChatEvent(player, content);
    }

    @Override
    public void handleChatEvent(Player player, String content) {
        // get user
        ChatUser chatUser = this.usersHolder.get(player.getUniqueId());

        // process the message
        ChatMessage<ChatUser, ChatChannel> chatMessage = this.bukkitChatMessageProcessor.process(chatUser, content);

        // dispatch the chat message
        this.chatMessageDispatcher.dispatch(chatMessage);
    }
}
