package com.yakovliam.freechat.channel;

import com.yakovliam.freechat.api.channel.Channel;
import com.yakovliam.freechat.api.user.User;

public class ChatChannel implements Channel {


    /**
     * The programmatic name of the channel
     */
    private final String handle;

    /**
     * Chat channel
     *
     * @param handle handle
     */
    public ChatChannel(String handle) {
        this.handle = handle;
    }

    @Override
    public String handle() {
        return this.handle;
    }

    @Override
    public <T> boolean canUse(User<T> user) {
        // todo implement
        return false;
    }

    @Override
    public <T> boolean canSee(User<T> user) {
        // todo implement
        return false;
    }
}
