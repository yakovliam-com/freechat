package com.yakovliam.freechat.listener;

import com.yakovliam.freechat.api.listener.ConnectionListener;
import com.yakovliam.freechat.api.user.UsersHolder;
import com.yakovliam.freechat.user.ChatUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitConnectionListener extends ConnectionListener<Player, ChatUser> implements Listener {

    /**
     * Bukkit connection listener
     *
     * @param usersHolder users holder
     */
    public BukkitConnectionListener(UsersHolder<ChatUser> usersHolder) {
        super(usersHolder);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.connectUser(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        this.disconnectUser(event.getPlayer());
    }

    @Override
    public ChatUser connectUser(Player player) {
        // create new chat user
        ChatUser chatUser = new ChatUser(player.getUniqueId(), player);
        // add the user to the users holder
        this.usersHolder.add(chatUser);

        return chatUser;
    }

    @Override
    public void disconnectUser(Player player) {
        // get the user from the users holder
        ChatUser user = this.usersHolder.get(player.getUniqueId());

        // return if the user is null
        if (user == null) {
            return;
        }

        this.usersHolder.remove(user);
    }
}
