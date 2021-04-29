package me.bigfatman.joe.packet.api;

import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.WrappedPacket;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInArmAnimation;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInBlockDig;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PacketManager {

    //I added these so i can add packet sniffing.
    public List<WrappedPacket> wrappedPackets = new ArrayList<>();
    public List<Packet> packets = new ArrayList<>();

    public final PlayerData data;

    public PacketManager(PlayerData data) {
        this.data = data;
    }

    public void handlePackets(Packet packet) {
        if (packet.object instanceof PacketPlayInArmAnimation) {
            WrappedPlayInArmAnimation armAnimation = new WrappedPlayInArmAnimation(packet.object.getClass());

            fireCheck(armAnimation);
            wrappedPackets.add(armAnimation);
        }

        if (packet.object instanceof PacketPlayInFlying)  {
            PacketPlayInFlying flying = (PacketPlayInFlying) packet.object;
            WrappedPlayInFlyingPacket flyingPacket = new WrappedPlayInFlyingPacket(packet.object.getClass());

            flyingPacket.x = flying.a();
            flyingPacket.y = flying.b();
            flyingPacket.z = flying.c();

            flyingPacket.pos = flying.g();
            flyingPacket.look = flying.h();

            flyingPacket.yaw = flying.d();
            flyingPacket.pitch = flying.e();

            data.locationData.handleLocation(flyingPacket);
            data.interactData.handleFlying(flyingPacket);
            fireCheck(flyingPacket);

            wrappedPackets.add(flyingPacket);
        }

        if (packet.object instanceof PacketPlayInUseEntity) {
            PacketPlayInUseEntity useEntity = (PacketPlayInUseEntity) packet.object;
            WrappedPlayInUseEntity playInUseEntity = new WrappedPlayInUseEntity(packet.object.getClass());

            switch (useEntity.a()) {
                case ATTACK:
                    playInUseEntity.action = WrappedPlayInUseEntity.Action.ATTACK;
                    break;
                case INTERACT:
                    playInUseEntity.action = WrappedPlayInUseEntity.Action.INTERACT;
                    break;
                case INTERACT_AT:
                    playInUseEntity.action = WrappedPlayInUseEntity.Action.INTERACT_AT;
                    break;
            }


            data.interactData.handleUseEntity(playInUseEntity);
            fireCheck(playInUseEntity);
            wrappedPackets.add(playInUseEntity);
        }

        if (packet.object instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig blockDig = (PacketPlayInBlockDig) packet.object;
            WrappedPlayInBlockDig playInBlockDig = new WrappedPlayInBlockDig(packet.object.getClass());

            switch (blockDig.c()) {
                case DROP_ITEM:
                    playInBlockDig.playerDigType = WrappedPlayInBlockDig.PlayerDigType.DROP_ITEM;
                    break;
                case DROP_ALL_ITEMS:
                    playInBlockDig.playerDigType = WrappedPlayInBlockDig.PlayerDigType.DROP_ALL_ITEMS;
                    break;
                case ABORT_DESTROY_BLOCK:
                    playInBlockDig.playerDigType = WrappedPlayInBlockDig.PlayerDigType.ABORT_DESTROY_BLOCK;
                    break;
                case STOP_DESTROY_BLOCK:
                    playInBlockDig.playerDigType = WrappedPlayInBlockDig.PlayerDigType.STOP_DESTROY_BLOCK;
                    break;
                case START_DESTROY_BLOCK:
                    playInBlockDig.playerDigType = WrappedPlayInBlockDig.PlayerDigType.START_DESTROY_BLOCK;
                    break;
                case RELEASE_USE_ITEM:
                    playInBlockDig.playerDigType = WrappedPlayInBlockDig.PlayerDigType.RELEASE_USE_ITEM;
                    break;
            }

            data.interactData.handleDigging(playInBlockDig);
            fireCheck(playInBlockDig);
            wrappedPackets.add(playInBlockDig);
        }
        packets.add(packet);
    }


    public void fireCheck(Object object) {
        synchronized (object) {
            this.data.checkManager.packetChecks
                    .stream().filter(Objects::nonNull)
                    .forEach(check -> check.handlePacket(object));
        }
    }
}
