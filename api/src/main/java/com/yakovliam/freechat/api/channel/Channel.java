package com.yakovliam.freechat.api.channel;

import com.yakovliam.freechat.api.user.User;

public interface Channel {

    /**
     * Returns the programmatic name of the channel
     *
     * @return handle
     */
    String handle();

    /**
     * Returns true if the current channel is the global channel
     *
     * @return is global
     */
    boolean isGlobal();

    /**
     * If a user can use this channel
     *
     * @param user user
     * @param <T>  type of user, platform specific
     * @return if a user can use this channel
     */
    <T> boolean canUse(User<T> user);

    /**
     * If a user can see this channel
     *
     * @param user user
     * @param <T>  type of user, platform specific
     * @return if a user can see this channel
     */
    <T> boolean canSee(User<T> user);
}
