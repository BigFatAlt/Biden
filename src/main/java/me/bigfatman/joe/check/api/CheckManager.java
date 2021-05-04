package me.bigfatman.joe.check.api;

import me.bigfatman.joe.check.impl.combat.aimassist.*;
import me.bigfatman.joe.check.impl.combat.killaura.KillAuraA;
import me.bigfatman.joe.check.impl.packet.InvalidPitch;
import me.bigfatman.joe.check.impl.packet.badpackets.BadPacketsA;
import me.bigfatman.joe.check.impl.packet.badpackets.BadPacketsB;
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
        packetChecks.add(new AimAssistA(playerData));
        packetChecks.add(new AimAssistA1(playerData));
        packetChecks.add(new AimAssistB(playerData));
        packetChecks.add(new AimAssistC(playerData));
        packetChecks.add(new AimAssistD(playerData));

        packetChecks.add(new BadPacketsA(playerData));
        packetChecks.add(new BadPacketsB(playerData));

        packetChecks.add(new KillAuraA(playerData));

        packetChecks.add(new InvalidPitch(playerData));
    }
}
