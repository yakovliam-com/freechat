package com.yakovliam.freechat.api.channel;

import com.yakovliam.freechat.api.user.User;

import java.util.List;

public abstract class ChannelSubscriptionsAdapter<T, C extends Channel, U extends User<?>> {

    /**
     * Provider that provides the subscriptions to the adapter
     * Usually extends a {@link ChannelSubscriptionsHolder}, but sometimes it can be a networking
     * class, like something that uses RabbitMQ or Redis
     */
    protected T provider;

    /**
     * Channels subscriptions adapter
     *
     * @param provider provider
     */
    public ChannelSubscriptionsAdapter(T provider) {
        this.provider = provider;
    }

    /**
     * Returns a list of subscribed channels by a player
     *
     * @param user user
     * @return list of subscribed channels
     */
    public abstract List<C> subscribedChannels(U user);

    /**
     * Subscribes a user to a channel
     *
     * @param user    user
     * @param channel channel
     */
    public abstract void subscribe(U user, C channel);

    /**
     * Unsubscribes a user to a channel
     *
     * @param user    user
     * @param channel channel
     */
    public abstract void unsubscribe(U user, C channel);

}
