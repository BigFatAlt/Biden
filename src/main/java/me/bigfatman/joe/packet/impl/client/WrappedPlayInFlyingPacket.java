package me.bigfatman.joe.packet.impl.client;


import me.bigfatman.joe.packet.api.PacketDirection;
import me.bigfatman.joe.packet.impl.WrappedPacket;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;

public class WrappedPlayInFlyingPacket extends WrappedPacket {

    public double x, y, z;
    public float yaw, pitch;
    public boolean pos, look;

    public long time;

    public WrappedPlayInFlyingPacket(Class<?> packetPlayInFlying) {
        super(PacketPlayInFlying.class);
        this.time = System.currentTimeMillis();
    }

    @Override
    public long time() {
        return time;
    }

    @Override
    public PacketDirection direction() {
        return PacketDirection.INBOUND;
    }
}
