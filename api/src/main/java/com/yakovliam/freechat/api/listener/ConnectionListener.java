package com.yakovliam.freechat.api.listener;

import com.yakovliam.freechat.api.user.User;
import com.yakovliam.freechat.api.user.UsersHolder;

public abstract class ConnectionListener<P, U extends User<P>> {

    /**
     * Users holder
     */
    protected final UsersHolder<U> usersHolder;

    /**
     * Connection listener
     *
     * @param usersHolder usersHolder
     */
    public ConnectionListener(UsersHolder<U> usersHolder) {
        this.usersHolder = usersHolder;
    }

    /**
     * Handles the connection of a user
     *
     * @param player player
     * @return user
     */
    public abstract U connectUser(P player);

    /**
     * Handles the disconnection of a user
     *
     * @param player player
     */
    public abstract void disconnectUser(P player);
}
