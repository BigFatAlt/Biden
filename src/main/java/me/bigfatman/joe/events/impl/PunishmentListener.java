package me.bigfatman.joe.events.impl;

import me.bigfatman.joe.Biden;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.events.api.PlayerBanEvent;
import me.bigfatman.joe.utils.Colour;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class PunishmentListener implements Listener {

    @EventHandler
    public void onBan(PlayerBanEvent event) {
        PlayerData data = Biden.INSTANCE.dataManager.find(event.player);

        new BukkitRunnable() {
            public void run() {
                //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + data.getBukkitPlayer().getName() + " " + event.check.name);
            }
        }.runTaskLater(Biden.INSTANCE.bidenPlugin, 30);

        for (String message : Biden.INSTANCE.bidenConfig.getConfig().getStringList("ban.message")) {

            Biden.INSTANCE.dataManager.playerDataMap.values()
                    .forEach(playerData -> playerData.getBukkitPlayer().sendMessage(Colour.translateColour(message)));
        }
    }
}
