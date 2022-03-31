package com.yakovliam.freechat.channel;

import com.yakovliam.freechat.api.channel.ChannelSubscriptionsHolder;
import com.yakovliam.freechat.user.ChatUser;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChatChannelSubscriptionsHolder extends ChannelSubscriptionsHolder<ChatChannel> {

    /**
     * Returns a list of subscribed channels
     *
     * @param user user
     * @return subscribed channels
     */
    public List<ChatChannel> subscribed(ChatUser user) {
        return this.subscribed((uuidListEntry -> uuidListEntry.getKey().equals(user.uuid())))
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
    public void subscribe(ChatUser user, ChatChannel channel) {
        this.subscribe(user.uuid(), channel);
    }

    /**
     * Unsubscribes a user from a channel
     *
     * @param user    user
     * @param channel channel
     */
    public void unsubscribe(ChatUser user, ChatChannel channel) {
        this.unsubscribe(user.uuid(), channel);
    }

}
