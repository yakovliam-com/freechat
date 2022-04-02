package com.yakovliam.freechat.api.channel;

import com.yakovliam.freechat.api.model.holder.MapHolder;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

public class ChannelsHolder<T extends Channel> extends MapHolder<String, T> {

    /**
     * Channels holder
     */
    public ChannelsHolder() {
        super(new HashMap<>());
    }

    /**
     * Returns a channel by the predicate
     *
     * @param predicate predicate
     * @return channel
     */
    protected Optional<T> firstChannelByPredicate(Predicate<? super T> predicate) {
        return this.firstValueByPredicate(predicate);
    }

    /**
     * Puts a channel in the holder
     *
     * @param channel channel
     */
    public void add(T channel) {
        this.held().put(channel.handle(), channel);
    }

    /**
     * Returns a user from the holder
     *
     * @param handle handle¬
     * @return user
     */
    public Optional<T> get(String handle) {
        return Optional.ofNullable(this.held().getOrDefault(handle, null));
    }
}
