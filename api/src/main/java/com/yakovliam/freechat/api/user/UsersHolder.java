package com.yakovliam.freechat.api.user;

import com.yakovliam.freechat.api.model.holder.MapHolder;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class UsersHolder<T extends User<?>> extends MapHolder<UUID, T> {

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
    public Optional<T> firstUserByPredicate(Predicate<? super T> predicate) {
        return this.firstValueByPredicate(predicate);
    }

    /**
     * Puts a user in the holder
     *
     * @param user user
     */
    public void add(T user) {
        this.held().put(user.uuid(), user);
    }

    /**
     * Removes a user from the holder
     *
     * @param user user
     */
    public void remove(T user) {
        this.held().remove(user.uuid());
    }

    /**
     * Returns a user from the holder
     *
     * @param uuid uuid
     * @return user
     */
    public T get(UUID uuid) {
        return this.held().getOrDefault(uuid, null);
    }
}
