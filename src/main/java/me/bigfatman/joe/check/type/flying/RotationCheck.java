package me.bigfatman.joe.check.type.flying;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.type.PacketCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;

public abstract class RotationCheck extends Check<Object> {

    public RotationCheck(PlayerData data) {
        super(data);
    }

    @Override
    public void handlePacket(Object object) {
        if (object instanceof WrappedPlayInFlyingPacket) {
            WrappedPlayInFlyingPacket flyingPacket = (WrappedPlayInFlyingPacket) object;

            if (flyingPacket.look) {
                rotationCheck(object);
            }
        }
    }

    public abstract void rotationCheck(Object object);
}
