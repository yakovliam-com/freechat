package com.yakovliam.freechat.api.util.dispatcher;

import com.yakovliam.freechat.api.channel.Channel;
import com.yakovliam.freechat.api.model.message.ChatMessage;
import com.yakovliam.freechat.api.user.User;

/**
 * This interface acts as a blueprint for any class that will be dispatching
 * messages in any capacity
 */
public interface ChatMessageDispatcher<U extends User<?>, C extends Channel> {

    /**
     * Dispatches a chat message to any medium
     * Usually dispatches to in-game chat, but sometimes it can be dispatched to a networking
     * protocol or service like RabbitMQ/Redis
     *
     * @param chatMessage chat message
     */
    void dispatch(ChatMessage<U, C> chatMessage);
}
