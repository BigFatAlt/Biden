package me.bigfatman.joe.mod;

import me.bigfatman.joe.Biden;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ModManager {

    private final Biden biden;

    public ModManager(Biden biden) {
        this.biden = biden;
    }

    public Map<Player, ModData> modDataMap = new HashMap<>();


    public ModData modData(Player player) {
        return modDataMap.get(player);
    }

    public boolean forge(Player player) {
        return modDataMap.containsKey(player);
    }
}
