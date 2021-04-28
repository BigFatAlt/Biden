package me.bigfatman.joe.data.impl;

import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInUseEntity;

public class InteractData {

    public long lastFlying;
    public int hitTicks;

    public void handleUseEntity(WrappedPlayInUseEntity useEntity) {
        if (useEntity.action.equals(WrappedPlayInUseEntity.Action.ATTACK)) {
            hitTicks = 0;
        }
    }

    public void handleFlying(WrappedPlayInFlyingPacket flyingPacket) {
        this.lastFlying = System.currentTimeMillis();
        this.hitTicks++;
    }
}
