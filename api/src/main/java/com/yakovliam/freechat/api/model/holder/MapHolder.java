package com.yakovliam.freechat.api.model.holder;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class MapHolder<K, V> {

    /**
     * The held map
     */
    private final Map<K, V> held;

    /**
     * Holder
     *
     * @param held the held map
     */
    public MapHolder(Map<K, V> held) {
        this.held = held;
    }

    /**
     * Returns the held map
     *
     * @return held map
     */
    protected Map<K, V> held() {
        return this.held;
    }

    /**
     * Returns a held value by the predicate
     *
     * @param predicate predicate
     * @return value
     */
    protected Optional<V> firstValueByPredicate(Predicate<? super V> predicate) {
        return this.held().values().stream().filter(predicate).findFirst();
    }
}
