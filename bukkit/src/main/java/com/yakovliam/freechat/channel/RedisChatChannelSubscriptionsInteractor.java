package com.yakovliam.freechat.channel;

import com.yakovliam.freechat.api.channel.ChannelSubscriptionsActor;
import com.yakovliam.freechat.user.ChatUser;

import java.util.Collections;
import java.util.List;

public class RedisChatChannelSubscriptionsInteractor extends ChannelSubscriptionsActor<ChatChannel, ChatUser> {

    @Override
    public List<ChatChannel> subscribed(ChatUser user) {
        // get a list of subscribed channels from redis
        return Collections.emptyList();
    }

    @Override
    public void subscribe(ChatUser user, ChatChannel channel) {
        // use redis to put the channel in the user's subscribed channels list
    }

    @Override
    public void unsubscribe(ChatUser user, ChatChannel channel) {
        // use redis to remove the channel from the user's subscribed channels list
    }
}
