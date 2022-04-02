package com.yakovliam.freechat.api.util.processor;

import com.yakovliam.freechat.api.model.message.ChatMessage;
import com.yakovliam.freechat.api.user.User;

/**
 * This is a utility message processing blueprint that handles the processing of messages
 * from their raw contents and sender into a chat message object
 */
public interface ChatMessageProcessor<U extends User<?>> {

    /**
     * Processes a player's chat message into a ChatMessage
     *
     * @param sender  the message sender
     * @param content the message content
     * @return chat message object
     */
    ChatMessage process(U sender, String content);
}
