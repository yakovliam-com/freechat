package com.yakovliam.freechat.channel;

import com.yakovliam.freechat.api.channel.ChannelSubscriptionsHolder;
import com.yakovliam.freechat.user.ChatUser;

import java.util.List;

/**
 * This class is a backend for the {@link com.yakovliam.freechat.api.channel.ChannelSubscriptionsAdapter}
 * Consequently of extending the {@link ChannelSubscriptionsHolder}, this class is also an
 * {@link com.yakovliam.freechat.api.channel.ChannelSubscriptionsActor}
 */
public class ChatChannelSubscriptionsHolder extends ChannelSubscriptionsHolder<ChatChannel, ChatUser> {

    @Override
    public List<ChatChannel> subscribed(ChatUser user) {
        return super.subscribed(user);
    }

    /**
     * Subscribes a user to a channel
     *
     * @param user    user
     * @param channel channel
     */
    @Override
    public void subscribe(ChatUser user, ChatChannel channel) {
        super.subscribe(user, channel);
    }

    /**
     * Unsubscribes a user from a channel
     *
     * @param user    user
     * @param channel channel
     */
    @Override
    public void unsubscribe(ChatUser user, ChatChannel channel) {
        super.unsubscribe(user, channel);
    }
}
