package com.yakovliam.freechat.api.format;

import com.yakovliam.freechat.api.model.holder.MapHolder;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

public class FormatsHolder<T extends Format> extends MapHolder<String, T> {

    /**
     * Formats holder
     */
    public FormatsHolder() {
        super(new HashMap<>());
    }

    /**
     * Returns a format by the predicate
     *
     * @param predicate predicate
     * @return format
     */
    public Optional<T> firstFormatByPredicate(Predicate<? super T> predicate) {
        return this.firstValueByPredicate(predicate);
    }

    /**
     * Puts a format in the holder
     *
     * @param format format
     */
    public void put(T format) {
        this.held().put(format.handle(), format);
    }
}
