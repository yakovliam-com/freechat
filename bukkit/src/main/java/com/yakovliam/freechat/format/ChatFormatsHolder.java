package com.yakovliam.freechat.format;

import com.yakovliam.freechat.api.format.FormatsHolder;
import com.yakovliam.freechat.config.ConfigProvider;
import com.yakovliam.freechat.user.ChatUser;

import java.util.Comparator;
import java.util.Map;

public class ChatFormatsHolder extends FormatsHolder<ChatFormat> {

    /**
     * Chat formats holder
     *
     * @param configProvider config provider
     */
    public ChatFormatsHolder(ConfigProvider configProvider) {
        // load formats from the config file
        configProvider.loadChatFormats().forEach(this::put);
    }

    /**
     * Returns the default chat format
     * todo use config.yml to get the default format handle and return that
     *
     * @return default format
     */
    public ChatFormat def() {
        return this.firstFormatByPredicate(format -> format.handle().equals("default"))
                .orElse(null);
    }

    /**
     * Returns the applicable chat format for a given user
     * // todo use the config.yml to determine a permission for each format
     *
     * @param user user
     * @return chat format
     */
    public ChatFormat get(ChatUser user) {
        return this.all().entrySet().stream()
                .max(Comparator.comparingInt(f -> f.getValue().priority()))
                .map(Map.Entry::getValue)
                .orElse(def());
    }
}
