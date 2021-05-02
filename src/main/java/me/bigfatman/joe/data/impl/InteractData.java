package me.bigfatman.joe.data.impl;

import me.bigfatman.joe.packet.impl.client.WrappedPlayInArmAnimation;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInBlockDig;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInUseEntity;

public class InteractData {

    public long lastFlying, lastDigging, lastArmAnimation;
    public boolean digging;
    public int hitTicks;

    public void handleUseEntity(WrappedPlayInUseEntity useEntity) {
        if (useEntity.action.equals(WrappedPlayInUseEntity.Action.ATTACK)) {
            hitTicks = 0;
        }
    }

    public void handleArmAnimation(WrappedPlayInArmAnimation armAnimation) {
        this.lastArmAnimation = System.currentTimeMillis();
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
    }


    public void handleFlying(WrappedPlayInFlyingPacket flyingPacket) {
        this.lastFlying = System.currentTimeMillis();

        this.hitTicks++;
    }
}
