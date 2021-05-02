package me.bigfatman.joe.check.impl.packet.badpackets;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.PacketCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInArmAnimation;

@CheckManifest(name = "BadPackets B", type = Check.CheckType.PACKET)
public class BadPacketsB extends PacketCheck {

    public int vl;

    public BadPacketsB(PlayerData data) {
        super(data);
    }

    @Override
    public void handlePacket(Object o) {
        if (o instanceof WrappedPlayInArmAnimation) {
            long lastArm = data.interactData.lastArmAnimation - data.interactData.lastFlying;

            if (lastArm < 30) {
                if (vl++ > 5) {
                    flag("lastArm+=" + lastArm);
                }
            }
        } else if (vl > 0) vl--;
    }
}
