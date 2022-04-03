package com.yakovliam.freechat.config.loader;

public interface ConfigLoader<V> {

    /**
     * Loads a V from a config
     *
     * @return V
     */
    V load();
}
