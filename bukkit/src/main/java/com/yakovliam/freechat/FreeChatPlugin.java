package com.yakovliam.freechat;

import com.yakovliam.freechat.api.format.FormatsHolder;
import com.yakovliam.freechat.api.user.UsersHolder;
import com.yakovliam.freechat.format.ChatFormat;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FreeChatPlugin extends JavaPlugin {

    /**
     * The plugin's instance of a users holder
     */
    private UsersHolder<Player> usersHolder;

    /**
     * The plugin's instance of a formats holder
     */
    private FormatsHolder<ChatFormat> formatsHolder;

    @Override
    public void onEnable() {
        this.usersHolder = new UsersHolder<>();
        this.formatsHolder = new FormatsHolder<>();
    }

    /**
     * Returns the plugin's users holder instance
     *
     * @return users holder
     */
    public UsersHolder<Player> usersHolder() {
        return this.usersHolder;
    }

    /**
     * Returns the plugin's formats holder instance
     *
     * @return formats holder
     */
    public FormatsHolder<ChatFormat> formatsHolder() {
        return this.formatsHolder;
    }
}
