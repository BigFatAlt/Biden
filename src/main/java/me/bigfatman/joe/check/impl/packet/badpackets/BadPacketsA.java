package me.bigfatman.joe.check.impl.packet.badpackets;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.PacketCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;

@CheckManifest(name = "BadPackets A", type = Check.CheckType.PACKET)
public class BadPacketsA extends PacketCheck {

    public int streak, vl;

    public BadPacketsA(PlayerData data) {
        super(data);
    }

    @Override
    public void handlePacket(Object o) {
        if (o instanceof WrappedPlayInFlyingPacket) {
            WrappedPlayInFlyingPacket flyingPacket = (WrappedPlayInFlyingPacket) o;

            if (flyingPacket.pos || data.getBukkitPlayer().isInsideVehicle()) {
                streak = 0;
            }

            if (streak++ > 20) {
                if (vl++ > 5) {
                    flag("streak+=" + streak);
                }
            } else if (vl++ > 0) vl--;
        }
    }
}
