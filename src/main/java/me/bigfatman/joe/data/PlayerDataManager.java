package me.bigfatman.joe.data;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    public final Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    public void addPlayer(Player player) {
        this.playerDataMap.put(player.getUniqueId(), new PlayerData(player.getUniqueId()));
    }

    public void removePlayer(Player player) {
        this.playerDataMap.remove(player.getUniqueId());
    }

    public PlayerData find(Player player) {
        return this.playerDataMap.get(player.getUniqueId());
    }
}
