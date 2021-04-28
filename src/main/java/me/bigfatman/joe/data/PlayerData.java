package me.bigfatman.joe.data;

import me.bigfatman.joe.check.api.CheckManager;
import me.bigfatman.joe.data.impl.InteractData;
import me.bigfatman.joe.data.impl.LocationData;
import me.bigfatman.joe.packet.api.PacketManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private Player bukkitPlayer;

    public boolean toggledAlerts, sniffingPackets;

    public CheckManager checkManager;
    public PacketManager packetManager;

    public LocationData locationData;
    public InteractData interactData;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;

        this.checkManager = new CheckManager(this);
        this.packetManager = new PacketManager(this);

        this.locationData = new LocationData();
        this.interactData = new InteractData();

        if (getBukkitPlayer().hasPermission("biden.alerts")) toggledAlerts = true;
    }

    public Player getBukkitPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}
