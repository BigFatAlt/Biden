package me.bigfatman.joe.data.impl;

import me.bigfatman.joe.packet.impl.client.WrappedPlayInBlockDig;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInUseEntity;
import org.bukkit.Bukkit;

public class InteractData {

    public long lastFlying, lastDigging;
    public boolean digging;
    public int hitTicks;

    public void handleUseEntity(WrappedPlayInUseEntity useEntity) {
        if (useEntity.action.equals(WrappedPlayInUseEntity.Action.ATTACK)) {
            hitTicks = 0;
        }
    }

    public void handleDigging(WrappedPlayInBlockDig blockDig) {
        switch (blockDig.playerDigType) {
            case START_DESTROY_BLOCK:
                lastDigging = System.currentTimeMillis();
                digging = true;
                break;
            case ABORT_DESTROY_BLOCK:
            case STOP_DESTROY_BLOCK:
                digging = false;
                break;
        }

        Bukkit.broadcastMessage(blockDig.playerDigType.name() + "");
    }


    public void handleFlying(WrappedPlayInFlyingPacket flyingPacket) {
        this.lastFlying = System.currentTimeMillis();
        this.hitTicks++;
    }
}
