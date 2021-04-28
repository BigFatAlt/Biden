package me.bigfatman.joe.violation;

import me.bigfatman.joe.Biden;
import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.events.api.PlayerBanEvent;
import me.bigfatman.joe.utils.Colour;
import me.bigfatman.joe.utils.MiscUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//Basic violation manager will update later on
public class ViolationManager {

    public List<Long> violations = new ArrayList<>();
    private double vl;

    public void handleViolation(Check check, PlayerData data) {
        long now = System.currentTimeMillis();

        if (violations.contains(now)) return;
        violations.add(now);

        vl++;

        String alertMessage = Biden.INSTANCE.bidenConfig.getConfig()
                .getString("alert.message")
                .replace("%name", data.getBukkitPlayer().getName())
                .replace("%check", check.name)
                .replace("%vl", String.valueOf(vl));

        Biden.INSTANCE.dataManager.playerDataMap.values().stream().filter
                (playerData -> playerData.getBukkitPlayer().hasPermission("biden.alerts") && playerData.toggledAlerts)
                .forEach(playerData -> playerData.getBukkitPlayer().sendMessage(Colour.translateColour(alertMessage)));
    }
}
