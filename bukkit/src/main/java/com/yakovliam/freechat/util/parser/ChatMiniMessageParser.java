package com.yakovliam.freechat.util.parser;

import com.yakovliam.freechat.format.ChatFormat;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

public class ChatMiniMessageParser {

    /**
     * Bukkit audiences
     */
    private final MiniMessage miniMessage;

    /**
     * Chat mini message parser
     */
    public ChatMiniMessageParser() {
        this.miniMessage = MiniMessage.miniMessage();
    }

    /**
     * Parses raw inputs (sender, content, and format) into a complete adventure component
     * All of these inputs are raw except the message content. This should already have links, etc.
     * filtered out by permission context
     *
     * @param sender  sender
     * @param content content
     * @param format  format
     * @return component
     */
    public Component parse(Player sender, ComponentLike content, ChatFormat format) {
        return format.parts().stream()
                .map(part -> PlaceholderAPI.setPlaceholders(sender, part))
                .map(part -> part.replace('\u00A7', '&')) // hacky workaround to UN-color the text because PlaceholderAPI messes it up
                .map(part -> miniMessage.deserialize(part, TagResolver.resolver("message", Tag.inserting(content))
                ))
                .collect(Component.toComponent());
    }
}
