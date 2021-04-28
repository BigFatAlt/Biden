package me.bigfatman.joe.events.api;

import me.bigfatman.joe.check.api.Check;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class PlayerBanEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public final Player player;
    public final Check check;

    public PlayerBanEvent(Player player, Check check) {
        this.player = player;
        this.check = check;
    }


    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
