package com.yakovliam.freechat.api.user;

import com.yakovliam.freechat.api.model.holder.MapHolder;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class UsersHolder<T> extends MapHolder<UUID, User<T>> {

    /**
     * Users holder
     */
    public UsersHolder() {
        super(new HashMap<>());
    }

    /**
     * Returns a user by the predicate
     *
     * @param predicate predicate
     * @return user
     */
    public Optional<User<T>> firstUserByPredicate(Predicate<? super User<T>> predicate) {
        return this.firstValueByPredicate(predicate);
    }

    /**
     * Puts a user in the holder
     *
     * @param user user
     */
    public void put(User<T> user) {
        this.held().put(user.uuid(), user);
    }
}
