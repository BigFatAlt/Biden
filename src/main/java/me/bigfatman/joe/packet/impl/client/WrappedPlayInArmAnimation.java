package me.bigfatman.joe.packet.impl.client;

import me.bigfatman.joe.packet.api.PacketDirection;
import me.bigfatman.joe.packet.impl.WrappedPacket;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;

public class WrappedPlayInArmAnimation extends WrappedPacket {

    public WrappedPlayInArmAnimation(Class<?> object) {
        super(PacketPlayInArmAnimation.class);
    }

    @Override
    public long time() {
        return System.currentTimeMillis();
    }

    @Override
    public PacketDirection direction() {
        return PacketDirection.INBOUND;
    }
}
