package com.yakovliam.freechat.user;

import com.yakovliam.freechat.api.user.User;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class ChatUser implements User<Player> {

    /**
     * The user's uuid
     */
    private final UUID uuid;

    /**
     * The player
     */
    private final Player player;

    /**
     * The last messaged user
     */
    private User<Player> lastMessaged;

    /**
     * Chat user
     *
     * @param uuid   uuid
     * @param player player
     */
    public ChatUser(UUID uuid, Player player) {
        this.uuid = uuid;
        this.player = player;
        this.lastMessaged = null;
    }

    @Override
    public UUID uuid() {
        return this.uuid;
    }

    @Override
    public Player player() {
        return this.player;
    }

    @Override
    public Optional<User<Player>> lastMessaged() {
        return Optional.ofNullable(this.lastMessaged);
    }

    @Override
    public void setLastMessaged(User<Player> user) {
        this.lastMessaged = user;
    }
}
