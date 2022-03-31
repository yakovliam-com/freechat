package com.yakovliam.freechat.api.channel;

import com.yakovliam.freechat.api.model.holder.MapHolder;
import com.yakovliam.freechat.api.user.User;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class ChannelSubscriptionsHolder<T extends Channel> extends MapHolder<UUID, List<T>> {

    /**
     * Channel subscriptions holder
     *
     * @param held the held map
     */
    public ChannelSubscriptionsHolder(Map<UUID, List<T>> held) {
        super(held);
    }

    /**
     * Channel subscriptions holder
     */
    public ChannelSubscriptionsHolder() {
        super(new HashMap<>());
    }


    /**
     * Returns a subscribed channels stream by an entry set predicate
     *
     * @param predicate predicate
     * @return channel entry set channels stream
     */
    protected Stream<Map.Entry<UUID, List<T>>> subscribed(Predicate<? super Map.Entry<UUID, List<T>>> predicate) {
        return this.held().entrySet().stream().filter(predicate);
    }

    /**
     * Subscribes a user to a channel
     *
     * @param uuid    uuid
     * @param channel channel
     */
    protected void subscribe(UUID uuid, T channel) {
        // get current subscribed list
        List<T> subscribed = this.held().getOrDefault(uuid, Collections.emptyList());

        if (subscribed.isEmpty()) {
            // create a new mutable array list
            List<T> updated = new ArrayList<>();
            updated.add(channel);

            this.held().put(uuid, updated);
        } else {
            // add the newly subscribed channel
            subscribed.add(channel);
        }
    }

    /**
     * Unsubscribes a user to a channel
     *
     * @param uuid    uuid
     * @param channel channel
     */
    protected void unsubscribe(UUID uuid, T channel) {
        // get current subscribed list
        List<T> subscribed = this.held().getOrDefault(uuid, Collections.emptyList());

        if (subscribed.isEmpty()) {
            return;
        }

        // remove the channel
        subscribed.removeIf(cur -> cur.handle().equals(channel.handle()));
    }


    /**
     * Puts a channel list in the holder
     *
     * @param user     user
     * @param channels channels
     */
    public <P> void put(User<P> user, List<T> channels) {
        this.held().put(user.uuid(), channels);
    }
}
