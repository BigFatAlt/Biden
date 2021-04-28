package me.bigfatman.joe.events.impl;

import me.bigfatman.joe.Biden;
import me.bigfatman.joe.data.PlayerData;
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
        PlayerData data = Biden.INSTANCE.dataManager.find(event.getPlayer());

        biden.packetInjector.injectPlayer(data);
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        PlayerData data = Biden.INSTANCE.dataManager.find(event.getPlayer());


        biden.packetInjector.ejectPlayer(data);
    }
}
