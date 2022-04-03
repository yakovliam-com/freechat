package com.yakovliam.freechat.config.loader.factory;

public interface Factory<V, C> {

    /**
     * Builds a V from context
     *
     * @param ctx context
     * @return V
     */
    V build(C ctx);
}
