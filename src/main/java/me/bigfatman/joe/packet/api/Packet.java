package me.bigfatman.joe.packet.api;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Packet {

    public final PacketDirection packetDirection;
    public final Object object;

}
