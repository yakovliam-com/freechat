package com.yakovliam.freechat.config.loader;

import com.yakovliam.freechat.config.loader.factory.ChatFormatFactory;
import com.yakovliam.freechat.format.ChatFormat;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class FormatsConfigLoader extends AutomaticConfigCreator implements ConfigLoader<List<ChatFormat>> {

    private static final String FORMATS_FILE_PATH = "formats.yml";

    /**
     * Chat format factor
     */
    private final ChatFormatFactory chatFormatFactory;

    /**
     * Formats root node
     */
    private final CommentedConfigurationNode formatsRoot;

    public FormatsConfigLoader(Path dataFolder) throws IOException {
        super(dataFolder, Path.of(FORMATS_FILE_PATH));

        // automatically create
        this.createIfNotExists();

        YamlConfigurationLoader defaultLoader = YamlConfigurationLoader.builder()
                .path(dataFolder.resolve(FORMATS_FILE_PATH))
                .build();

        // load the formats config
        CommentedConfigurationNode root = defaultLoader.load();
        this.formatsRoot = root.node("formats");

        // create the factory
        this.chatFormatFactory = new ChatFormatFactory(formatsRoot);
    }

    @Override
    public List<ChatFormat> load() {
        // get a list of keys
        List<String> keys = formatsRoot.childrenList().stream()
                .map(node -> Objects.requireNonNull(node.key()).toString())
                .toList();

        // loop through keys and build from the factory
        return keys.stream()
                .map(this.chatFormatFactory::build)
                .toList();
    }
}
