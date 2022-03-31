package com.yakovliam.freechat.channel;

import com.yakovliam.freechat.api.channel.ChannelSubscriptionsAdapter;
import com.yakovliam.freechat.user.ChatUser;

import java.util.List;

public class MemoryChannelSubscriptionsAdapter extends ChannelSubscriptionsAdapter<ChatChannelSubscriptionsHolder, ChatChannel, ChatUser> {

    public MemoryChannelSubscriptionsAdapter(ChatChannelSubscriptionsHolder provider) {
        super(provider);
    }

    @Override
    public List<ChatChannel> subscribedChannels(ChatUser user) {
        return provider.subscribed(user);
    }

    @Override
    public void subscribe(ChatUser user, ChatChannel chatChannel) {
        provider.subscribe(user, chatChannel);
    }

    @Override
    public void unsubscribe(ChatUser user, ChatChannel chatChannel) {
        provider.unsubscribe(user, chatChannel);
    }
}
