package com.yakovliam.freechat.api.channel;

import com.yakovliam.freechat.api.user.User;

import java.util.List;

/**
 * An adapter class that acts as the access point (frontend) for any other classes that want to
 * subscribe/unsubscribe/get a list of subscribed channels for a user
 *
 * @param <T> the provider type
 * @param <C> the channel list type
 * @param <U> the user type
 */
public class ChannelSubscriptionsAdapter<T extends ChannelSubscriptionsActor<C, U>, C extends Channel, U extends User<?>> {

    /**
     * Provider that provides the subscriptions to the adapter
     * Usually extends a {@link ChannelSubscriptionsHolder}, but sometimes it can be a networking
     * class, like something that uses RabbitMQ or Redis
     */
    protected T providerActor;

    /**
     * Channels subscriptions adapter
     *
     * @param providerActor provider
     */
    public ChannelSubscriptionsAdapter(T providerActor) {
        this.providerActor = providerActor;
    }

    /**
     * Returns a list of subscribed channels through the actor
     *
     * @param user user
     * @return list of subscribed channels
     */
    public List<C> subscribed(U user) {
        return this.providerActor.subscribed(user);
    }

    /**
     * Subscribes a user to a channel through the actor
     *
     * @param user    user
     * @param channel channel
     */
    public void subscribe(U user, C channel) {
        this.providerActor.subscribe(user, channel);
    }

    /**
     * Unsubscribes a user to a channel through the actor
     *
     * @param user    user
     * @param channel channel
     */
    public void unsubscribe(U user, C channel) {
        this.providerActor.unsubscribe(user, channel);
    }
}
