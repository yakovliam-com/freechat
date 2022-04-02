package com.yakovliam.freechat.format;

import com.yakovliam.freechat.api.format.FormatsHolder;

import java.util.List;

public class ChatFormatsHolder extends FormatsHolder<ChatFormat> {
    // todo load formats from the configuration file

    // todo remove this method, it's just a placeholder
    public ChatFormat generateDefault() {
        return new ChatFormat("default", Integer.MAX_VALUE,
                List.of(
                        "<click:suggest_command:'/msg %player_name%'><hover:show_text:'<aqua>Click to message %player_name%</aqua>'><gray>%player_name%</hover></click>",
                        "<gray>: ",
                        "<white><message>"
                )
        );
    }
}
