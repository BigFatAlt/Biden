package me.bigfatman.joe.mod.impl;

import me.bigfatman.joe.Biden;
import me.bigfatman.joe.mod.ModManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ModListener implements Listener {

    public final ModManager modManager;

    public ModListener(ModManager modManager) {
        this.modManager = modManager;

        Bukkit.getMessenger().registerOutgoingPluginChannel(Biden.INSTANCE.bidenPlugin, "FML|HS");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                sendFmlPacket(player, (byte) -2, (byte) 0);
                sendFmlPacket(player, (byte) 0, (byte) 2, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
                sendFmlPacket(player, (byte) 2, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
            }
        }.runTaskLater(Biden.INSTANCE.bidenPlugin, 20L);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Biden.INSTANCE.modManager.modDataMap.remove(event.getPlayer());
    }

    public void sendFmlPacket(Player player, byte... data) {
        player.sendPluginMessage(Biden.INSTANCE.bidenPlugin, "FML|HS", data);
    }


}
