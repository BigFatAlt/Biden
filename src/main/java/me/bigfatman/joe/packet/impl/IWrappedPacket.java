package me.bigfatman.joe.packet.impl;

import me.bigfatman.joe.packet.api.PacketDirection;

public interface IWrappedPacket {

    public long time();
    public PacketDirection direction();
}
