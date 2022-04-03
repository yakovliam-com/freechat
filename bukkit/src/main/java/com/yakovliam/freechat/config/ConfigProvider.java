package com.yakovliam.freechat.config;

import com.yakovliam.freechat.config.loader.FormatsConfigLoader;
import com.yakovliam.freechat.format.ChatFormat;
import org.spongepowered.configurate.ConfigurateException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class ConfigProvider {

    /**
     * Formats config loader
     */
    private final FormatsConfigLoader formatsConfigLoader;

    /**
     * Config provider
     *
     * @param dataFolder the plugin's data folder
     */
    public ConfigProvider(Path dataFolder) throws IOException {
        this.formatsConfigLoader = new FormatsConfigLoader(dataFolder);
    }

    /**
     * Loads chat formats
     *
     * @return chat formats
     */
    public List<ChatFormat> loadChatFormats() {
        return this.formatsConfigLoader.load();
    }
}
