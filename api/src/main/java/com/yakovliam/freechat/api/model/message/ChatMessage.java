package com.yakovliam.freechat.api.model.message;

import com.yakovliam.freechat.api.channel.Channel;
import com.yakovliam.freechat.api.user.User;
import net.kyori.adventure.text.Component;

import java.util.List;

/**
 * This object is a bundle of information about a
 * chat message that will be disassembled and distributed
 * <p>
 * This object exists to be serialized and sent over networking protocols if needed
 */
public class ChatMessage<U extends User<?>, C extends Channel> {

    /**
     * The sender of the message
     */
    private final U sender;

    /**
     * The target channels to send the chat message to
     */
    private final List<C> targets;

    /**
     * The message content in serialized form
     */
    private final Component content;

    /**
     * Chat message
     *
     * @param sender  sender
     * @param targets targets
     * @param content content
     */
    public ChatMessage(U sender, List<C> targets, Component content) {
        this.sender = sender;
        this.targets = targets;
        this.content = content;
    }

    public U sender() {
        return sender;
    }

    public List<C> targets() {
        return targets;
    }

    public Component content() {
        return content;
    }
}
