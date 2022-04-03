package com.yakovliam.freechat.config.loader;

import com.yakovliam.freechat.FreeChatPlugin;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class AutomaticConfigCreator {

    private final Path dataFolder;

    private final Path configPath;

    public AutomaticConfigCreator(Path dataFolder, Path configPath) {
        this.dataFolder = dataFolder;
        this.configPath = configPath;
    }

    protected void createIfNotExists() throws IOException {
        if (!Files.exists(dataFolder)) {
            Files.createDirectories(dataFolder);
        }

        if (!Files.exists(dataFolder.resolve(configPath))) {
            URL resource = getClass().getClassLoader().getResource(this.configPath.toString());
            Files.copy(Objects.requireNonNull(resource).openStream(), dataFolder.resolve(configPath));
        }
    }
}
