package com.yakovliam.freechat.config.loader.factory;

import com.yakovliam.freechat.format.ChatFormat;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.List;

/**
 * Factory that builds chat formats given the chat format's handle
 */
public class ChatFormatFactory implements Factory<ChatFormat, String> {

    /**
     * The section of the config dedicated to listing formats
     */
    private final CommentedConfigurationNode formatsSection;

    /**
     * Chat format factory
     *
     * @param formatsSection formats section
     */
    public ChatFormatFactory(CommentedConfigurationNode formatsSection) {
        this.formatsSection = formatsSection;
    }

    @Override
    public ChatFormat build(String ctx) {
        // the format's root
        CommentedConfigurationNode formatRoot = formatsSection.node(ctx);

        // get the priority
        int priority = formatRoot.node("priority").getInt();
        // get the parts
        List<String> parts;
        try {
            parts = formatRoot.node("parts").getList(String.class);
        } catch (SerializationException e) {
            e.printStackTrace();
            return null;
        }

        return new ChatFormat(ctx, priority, parts);
    }
}
