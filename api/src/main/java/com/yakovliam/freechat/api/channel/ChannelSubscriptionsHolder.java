package com.yakovliam.freechat.api.channel;

import com.yakovliam.freechat.api.model.holder.MapHolder;
import com.yakovliam.freechat.api.user.User;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A holder class that acts as the backend for a {@link ChannelSubscriptionsAdapter}
 * This class actually holds a list of subscribed channels (mapped to user uuids) in memory
 * When using Redis/other networking, this class will be entirely unused because this class is ONLY
 * for IN-MEMORY storage
 *
 * @param <T> the channel type
 */
public abstract class ChannelSubscriptionsHolder<T extends Channel, U extends User<?>> extends ChannelSubscriptionsActor<T, U> {

    private final ChannelMapHolder<T> channelMapHolder;

    /**
     * Channel subscriptions holder
     *
     * @param held the held map
     */
    public ChannelSubscriptionsHolder(Map<UUID, List<T>> held) {
        this.channelMapHolder = new ChannelMapHolder<>(held);
    }

    /**
     * Channel subscriptions holder
     */
    public ChannelSubscriptionsHolder() {
        this.channelMapHolder = new ChannelMapHolder<>();
    }

    /**
     * Returns a subscribed channels stream by an entry set predicate
     *
     * @param predicate predicate
     * @return channel entry set channels stream
     */
    protected Stream<Map.Entry<UUID, List<T>>> filterSubscribed(Predicate<? super Map.Entry<UUID, List<T>>> predicate) {
        return this.channelMapHolder.all().entrySet().stream().filter(predicate);
    }

    /**
     * Fetches a user's subscribed channels
     *
     * @param user user user
     * @return subscribed channels
     */
    @Override
    public List<T> subscribed(U user) {
        return this.filterSubscribed(uuidListEntry -> uuidListEntry.getKey().equals(user.uuid()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(Collections.emptyList());
    }

    /**
     * Subscribes a user to a channel
     *
     * @param user    user
     * @param channel channel
     */
    @Override
    public void subscribe(U user, T channel) {
        // get current subscribed list
        List<T> subscribed = this.channelMapHolder.all().getOrDefault(user.uuid(), Collections.emptyList());

        if (subscribed.isEmpty()) {
            // create a new mutable array list
            List<T> updated = new ArrayList<>();
            updated.add(channel);

            this.channelMapHolder.all().put(user.uuid(), updated);
        } else {
            // add the newly subscribed channel
            subscribed.add(channel);
        }
    }

    /**
     * Unsubscribes a user to a channel
     *
     * @param user    user
     * @param channel channel
     */
    @Override
    public void unsubscribe(U user, T channel) {
        // get current subscribed list
        List<T> subscribed = this.channelMapHolder.all().getOrDefault(user.uuid(), Collections.emptyList());

        if (subscribed.isEmpty()) {
            return;
        }

        // remove the channel
        subscribed.removeIf(cur -> cur.handle().equals(channel.handle()));
    }

    static class ChannelMapHolder<T extends Channel> extends MapHolder<UUID, List<T>> {

        /**
         * Holder
         *
         * @param held the held map
         */
        public ChannelMapHolder(Map<UUID, List<T>> held) {
            super(held);
        }

        /**
         * Holder
         */
        public ChannelMapHolder() {
            super(new HashMap<>());
        }

        /**
         * Public accessible abstraction method to allow for grabbing of the held map
         *
         * @return held
         */
        public Map<UUID, List<T>> all() {
            return this.held();
        }
    }
}
