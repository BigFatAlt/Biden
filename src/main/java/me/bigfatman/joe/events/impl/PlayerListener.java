package me.bigfatman.joe.events.impl;

import me.bigfatman.joe.Biden;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final Biden biden;

    public PlayerListener(Biden biden) {
        this.biden = biden;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        biden.dataManager.addPlayer(event.getPlayer());
        biden.packetInjector.injectPlayer(biden.dataManager.find(player));
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        biden.dataManager.removePlayer(event.getPlayer());
        biden.packetInjector.ejectPlayer(biden.dataManager.find(player));
    }
}
