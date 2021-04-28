package me.bigfatman.joe.check.api;

import me.bigfatman.joe.check.impl.packet.InvalidPitch;
import me.bigfatman.joe.check.type.PacketCheck;
import me.bigfatman.joe.data.PlayerData;

import java.util.ArrayList;
import java.util.List;

public class CheckManager {

    public List<Check<Object>> packetChecks = new ArrayList<>();

    public final PlayerData playerData;

    public CheckManager(PlayerData playerData) {
        this.playerData = playerData;

        registerPacket();
    }

    public void registerPacket() {
        packetChecks.add(new InvalidPitch(playerData));
    }
}
