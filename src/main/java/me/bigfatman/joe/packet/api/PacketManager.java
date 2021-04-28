package me.bigfatman.joe.packet.api;

import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.WrappedPacket;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PacketManager {

    public List<WrappedPacket> wrappedPackets = new ArrayList<>();
    public List<Packet> packets = new ArrayList<>();

    public final PlayerData data;

    public PacketManager(PlayerData data) {
        this.data = data;
    }

    public void handlePackets(Packet packet) {
        if (packet.object instanceof PacketPlayInFlying)  {
            PacketPlayInFlying flying = (PacketPlayInFlying) packet.object;
            WrappedPlayInFlyingPacket flyingPacket = new WrappedPlayInFlyingPacket(packet.object.getClass());

            flyingPacket.x = flying.a();
            flyingPacket.y = flying.b();
            flyingPacket.z = flying.c();

            flyingPacket.yaw = flying.d();
            flyingPacket.pitch = flying.e();

            fireCheck(flyingPacket);
        }
    }

    public void fireCheck(Object object) {
        synchronized (object) {
            this.data.checkManager.packetChecks
                    .stream().filter(Objects::nonNull)
                    .forEach(check -> check.handlePacket(object));
        }
    }
}
