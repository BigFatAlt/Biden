package me.bigfatman.joe.packet.impl.client;

import me.bigfatman.joe.packet.api.PacketDirection;
import me.bigfatman.joe.packet.impl.WrappedPacket;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.entity.Entity;

public class WrappedPlayInUseEntity extends WrappedPacket {

    public PacketPlayInUseEntity.EnumEntityUseAction useEntityAction;
    public Entity entity;

    public WrappedPlayInUseEntity(Class<?> object) {
        super(PacketPlayInUseEntity.class);
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
