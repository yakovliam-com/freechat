package com.yakovliam.freechat.format;

import com.yakovliam.freechat.api.format.Format;

import java.util.List;

public class ChatFormat implements Format {

    /**
     * The handle of the format
     */
    private final String handle;

    /**
     * The priority of the format
     */
    private final int priority;

    /**
     * The parts of the format
     */
    private final List<String> parts;

    /**
     * Chat format
     *
     * @param handle   handle
     * @param priority priority
     * @param parts    parts
     */
    public ChatFormat(String handle, int priority, List<String> parts) {
        this.handle = handle;
        this.priority = priority;
        this.parts = parts;
    }

    @Override
    public String handle() {
        return this.handle;
    }

    @Override
    public int priority() {
        return this.priority;
    }

    @Override
    public List<String> parts() {
        return this.parts;
    }
}
