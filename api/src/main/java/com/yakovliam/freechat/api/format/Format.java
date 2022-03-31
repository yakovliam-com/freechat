package com.yakovliam.freechat.api.format;

import java.util.List;

public interface Format {

    /**
     * Returns the programmatic name of the format
     *
     * @return handle
     */
    String handle();

    /**
     * Returns the format's priority
     *
     * @return priority
     */
    int priority();

    /**
     * Returns a list of format parts in minimessage format
     *
     * @return parts
     */
    List<String> parts();
}
