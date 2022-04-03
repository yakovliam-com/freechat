package com.yakovliam.freechat;

import com.yakovliam.freechat.channel.ChatChannelSubscriptionsAdapter;
import com.yakovliam.freechat.channel.ChatChannelSubscriptionsHolder;
import com.yakovliam.freechat.channel.ChatChannelsHolder;
import com.yakovliam.freechat.config.ConfigProvider;
import com.yakovliam.freechat.format.ChatFormatsHolder;
import com.yakovliam.freechat.listener.BukkitChatListener;
import com.yakovliam.freechat.listener.BukkitConnectionListener;
import com.yakovliam.freechat.user.ChatUsersHolder;
import com.yakovliam.freechat.util.BukkitChatMessageProcessor;
import com.yakovliam.freechat.util.dispatcher.BukkitChatMessageDispatcher;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class FreeChatPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // initialize adventure
        /*
         * The plugin's instance of Adventure's Bukkit Audiences
         */
        BukkitAudiences bukkitAudiences = BukkitAudiences.create(this);

        // load config provider
        ConfigProvider configProvider;
        try {
            configProvider = new ConfigProvider(this.getDataFolder().toPath());
        } catch (IOException e) {
            e.printStackTrace();
            // stop in it's tracks...
            // can't do anything without formats, etc...
            return;
        }

        /*
         * The plugin's instance of a users holder
         */
        ChatUsersHolder usersHolder = new ChatUsersHolder();

        /*
         * The plugin's instance of a formats holder
         */
        ChatFormatsHolder formatsHolder = new ChatFormatsHolder(configProvider);

        /*
         * The plugin's instance of the channels holder
         */
        ChatChannelsHolder channelsHolder = new ChatChannelsHolder();

        // instantiate a new memory subscriptions adapter
        // in the future this won't be a 'memory' channel adapter, but rather
        // it will be connected to some kind of 'determining' method that sets it to an actor class that
        // uses whatever storage medium we want
        /*
         * The plugin's channel subscriptions adapter
         */
        ChatChannelSubscriptionsAdapter<?> channelSubscriptionsAdapter = new ChatChannelSubscriptionsAdapter<>(new ChatChannelSubscriptionsHolder());

        // create chat message processor
        BukkitChatMessageProcessor bukkitChatMessageProcessor = new BukkitChatMessageProcessor(formatsHolder, channelSubscriptionsAdapter);
        // create chat message dispatcher
        BukkitChatMessageDispatcher bukkitChatMessageDispatcher = new BukkitChatMessageDispatcher(bukkitAudiences);

        // register connection listener
        this.getServer().getPluginManager().registerEvents(new BukkitConnectionListener(usersHolder, channelSubscriptionsAdapter), this);
        // register chat listener
        this.getServer().getPluginManager().registerEvents(new BukkitChatListener(usersHolder, bukkitChatMessageProcessor, bukkitChatMessageDispatcher), this);
    }
}
