package com.yakovliam.freechat.util;

import com.yakovliam.freechat.api.model.message.ChatMessage;
import com.yakovliam.freechat.api.util.processor.ChatMessageProcessor;
import com.yakovliam.freechat.channel.ChatChannel;
import com.yakovliam.freechat.channel.ChatChannelSubscriptionsAdapter;
import com.yakovliam.freechat.format.ChatFormat;
import com.yakovliam.freechat.format.ChatFormatsHolder;
import com.yakovliam.freechat.user.ChatUser;
import com.yakovliam.freechat.util.parser.ChatMiniMessageParser;
import net.kyori.adventure.text.Component;

import java.util.List;

public class BukkitChatMessageProcessor implements ChatMessageProcessor<ChatUser> {

    /**
     * Formats holder
     */
    private final ChatFormatsHolder formatsHolder;

    /**
     * Channel subscriptions adapter
     */
    private final ChatChannelSubscriptionsAdapter<?> channelSubscriptionsAdapter;

    /**
     * Chat MiniMessage parser
     */
    private final ChatMiniMessageParser chatMiniMessageParser = new ChatMiniMessageParser();

    public BukkitChatMessageProcessor(ChatFormatsHolder formatsHolder, ChatChannelSubscriptionsAdapter<?> channelSubscriptionsAdapter) {
        this.formatsHolder = formatsHolder;
        this.channelSubscriptionsAdapter = channelSubscriptionsAdapter;
    }

    @Override
    public ChatMessage<ChatUser, ChatChannel> process(ChatUser user, String content) {
        // determine the channels to send the message in
        //todo - in the future have a specific parameter for target channels
        // users will probably use commands like "/send <channel> <message>" or
        // prefixed chat messages like "# this is a message in the staff channel" so we wouldn't
        // be able to determine the target channel based on the user's current subscribed channels
        List<ChatChannel> subscribed = this.channelSubscriptionsAdapter.subscribed(user);

        // determine the applicable format
        ChatFormat format = this.formatsHolder.get(user);

        // parse the format into a component
        // todo parse the content using replacers & permission context to remove links, etc.
        Component parsed = chatMiniMessageParser.parse(user.player(), Component.text(content), format);

        // construct a chat message
        return new ChatMessage<>(user, subscribed, parsed);
    }
}
