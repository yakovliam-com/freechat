package com.yakovliam.freechat.api.channel;

import com.yakovliam.freechat.api.user.User;

import java.util.List;

public abstract class ChannelSubscriptionsActor<C extends Channel, U extends User<?>> {

    /**
     * Fetches subscribed channels
     *
     * @param user user
     * @return channels
     */
    public abstract List<C> subscribed(U user);

    /**
     * Push a subscription to a channel
     *
     * @param user    user
     * @param channel channel
     */
    public abstract void subscribe(U user, C channel);

    /**
     * Push an unsubscription from a channel
     *
     * @param user    user
     * @param channel channel
     */
    public abstract void unsubscribe(U user, C channel);
}
