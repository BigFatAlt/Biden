package me.bigfatman.joe.data;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    public final Map<UUID, PlayerData> playerDataMap = new HashMap<>();

    public PlayerData find(Player player) {
        if (!playerDataMap.containsKey(player.getUniqueId())) {
            this.playerDataMap.put(player.getUniqueId(), new PlayerData(player.getUniqueId()));
        }

        return this.playerDataMap.get(player.getUniqueId());
    }
}
