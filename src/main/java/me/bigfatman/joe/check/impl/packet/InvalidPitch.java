package me.bigfatman.joe.check.impl.packet;

import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.PacketCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInArmAnimation;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;

@CheckManifest(name = "InvalidPackets")
public class InvalidPitch extends PacketCheck {

    public InvalidPitch(PlayerData data) {
        super(data);
    }

    @Override
    public void handlePacket(Object o) {

    }
}
