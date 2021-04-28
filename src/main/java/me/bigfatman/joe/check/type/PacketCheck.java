package me.bigfatman.joe.check.type;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.data.PlayerData;

public class PacketCheck extends Check<Object> {

    public PacketCheck(PlayerData data) {
        super(data);
    }

    @Override
    public void handlePacket(Object o) {}
}
