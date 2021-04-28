package me.bigfatman.joe.packet.impl.client;

import me.bigfatman.joe.packet.api.PacketDirection;
import me.bigfatman.joe.packet.impl.WrappedPacket;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.entity.Entity;

public class WrappedPlayInUseEntity extends WrappedPacket {

    public Action action;
    public Entity entity;

    public WrappedPlayInUseEntity(Class<?> object) {
        super(PacketPlayInUseEntity.class);
    }


    public enum Action {
        ATTACK,
        INTERACT,
        INTERACT_AT
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
