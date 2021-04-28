package me.bigfatman.joe.check.type.flying;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.type.PacketCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;

public abstract class PositionCheck extends Check<Object> {

    public PositionCheck(PlayerData data) {
        super(data);
    }

    @Override
    public void handlePacket(Object object) {
        if (object instanceof WrappedPlayInFlyingPacket) {
            WrappedPlayInFlyingPacket flyingPacket = (WrappedPlayInFlyingPacket) object;

            if (flyingPacket.look) {
                positionCheck(object);
            }
        }
    }

    public abstract void positionCheck(Object object);

}
