package com.yakovliam.freechat.channel;

import com.yakovliam.freechat.api.channel.ChannelSubscriptionsActor;
import com.yakovliam.freechat.api.channel.ChannelSubscriptionsAdapter;
import com.yakovliam.freechat.user.ChatUser;

import java.util.List;

/**
 * This class is an adapter that allows for interaction with a medium that either
 * 1) interacts with memory
 * 2) interacts with a networking service (like Redis/RabbitMQ)
 * <p>
 * From using a generic that is an actor, the provider is the class that has access to the low-level medium
 *
 * @param <T> an actor that acts as a mediator between this adapter and the backend
 */
public class ChatChannelSubscriptionsAdapter<T extends ChannelSubscriptionsActor<ChatChannel, ChatUser>>
        extends ChannelSubscriptionsAdapter<T, ChatChannel, ChatUser> {

    public ChatChannelSubscriptionsAdapter(T provider) {
        super(provider);
    }

    @Override
    public List<ChatChannel> subscribed(ChatUser user) {
        return provider.subscribed(user);
    }

    @Override
    public void subscribe(ChatUser user, ChatChannel channel) {
        this.provider.subscribe(user, channel);
    }

    @Override
    public void unsubscribe(ChatUser user, ChatChannel channel) {
        this.provider.unsubscribe(user, channel);
    }
}
