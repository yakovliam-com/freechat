package com.yakovliam.freechat;

import com.yakovliam.freechat.api.format.FormatsHolder;
import com.yakovliam.freechat.api.user.UsersHolder;
import com.yakovliam.freechat.channel.ChatChannelSubscriptionsHolder;
import com.yakovliam.freechat.channel.MemoryChannelSubscriptionsAdapter;
import com.yakovliam.freechat.format.ChatFormat;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FreeChatPlugin extends JavaPlugin {

    /**
     * The plugin's instance of a users holder
     */
    private UsersHolder<Player> usersHolder;

    /**
     * The plugin's instance of a formats holder
     */
    private FormatsHolder<ChatFormat> formatsHolder;

    /**
     * The plugin's channel subscriptions adapter
     */
    private MemoryChannelSubscriptionsAdapter channelSubscriptionsAdapter;

    @Override
    public void onEnable() {
        this.usersHolder = new UsersHolder<>();
        this.formatsHolder = new FormatsHolder<>();

        // instantiate a new memory subscriptions adapter
        // in the future this won't be a 'memory' channel adapter, but rather
        // just a channel subscriptions adapter with no inferred type, so we can determine whether
        // we want to use redis/rabbitmq/mqtt in place of in memory
        this.channelSubscriptionsAdapter = new MemoryChannelSubscriptionsAdapter(new ChatChannelSubscriptionsHolder());
    }

    /**
     * Returns the plugin's users holder instance
     *
     * @return users holder
     */
    public UsersHolder<Player> usersHolder() {
        return this.usersHolder;
    }

    /**
     * Returns the plugin's formats holder instance
     *
     * @return formats holder
     */
    public FormatsHolder<ChatFormat> formatsHolder() {
        return this.formatsHolder;
    }

    /**
     * Returns this plugin's memory channel subscriptions adapter
     *
     * @return adapter
     */
    public MemoryChannelSubscriptionsAdapter channelSubscriptionsAdapter() {
        return this.channelSubscriptionsAdapter;
    }
}
