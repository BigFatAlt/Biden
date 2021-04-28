package me.bigfatman.joe.check.impl.packet;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.flying.RotationCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;

@CheckManifest(name = "InvalidPitch", type = Check.CheckType.PACKET)
public class InvalidPitch extends RotationCheck {

    public InvalidPitch(PlayerData data) {
        super(data);
    }

    @Override
    public void rotationCheck(Object object) {
        WrappedPlayInFlyingPacket flyingPacket = (WrappedPlayInFlyingPacket) object;

        if (Math.abs(flyingPacket.pitch) > 90.f) {
            flag("P+=" + Math.abs(flyingPacket.pitch));
        }
    }
}
