package com.yakovliam.freechat.api.listener;

public abstract class ChatListener<P> {

    /**
     * Handles a chat event
     *
     * @param player  the sender of the chat message
     * @param message chat message content
     */
    public abstract void handleChatEvent(P player, String message);
}

