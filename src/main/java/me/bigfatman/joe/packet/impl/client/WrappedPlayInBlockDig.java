package me.bigfatman.joe.packet.impl.client;

import me.bigfatman.joe.packet.api.PacketDirection;
import me.bigfatman.joe.packet.impl.WrappedPacket;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;

public class WrappedPlayInBlockDig extends WrappedPacket {

    public PlayerDigType playerDigType;

    public WrappedPlayInBlockDig(Class<?> object) {
        super(PacketPlayInBlockDig.class);
    }

    public enum PlayerDigType {
        START_DESTROY_BLOCK,
        ABORT_DESTROY_BLOCK,
        STOP_DESTROY_BLOCK,
        DROP_ALL_ITEMS,
        DROP_ITEM,
        RELEASE_USE_ITEM;
    }


    @Override
    public long time() {
        return 0;
    }

    @Override
    public PacketDirection direction() {
        return null;
    }
}
