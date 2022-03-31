package com.yakovliam.freechat.api.user;

import java.util.Optional;
import java.util.UUID;

public interface User<T> {

    /**
     * Return the user's uuid
     *
     * @return the user's uuid
     */
    UUID uuid();

    /**
     * Returns the player object of the respective platform
     *
     * @return player
     */
    T player();

    /**
     * Returns the user that was last messaged
     *
     * @return the last messaged user
     */
    Optional<User<T>> lastMessaged();

    /**
     * Sets the last messaged user
     *
     * @param user user
     */
    void setLastMessaged(User<T> user);
}
