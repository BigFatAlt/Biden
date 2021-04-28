package me.bigfatman.joe.packet.api;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;

import java.util.Objects;

public class PacketHandler extends ChannelDuplexHandler {

    private final PlayerData data;

    public PacketHandler(PlayerData data) {
        this.data = data;
    }



    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        try {
            Packet packet = new Packet(PacketDirection.INBOUND, o);
            data.packetManager.handlePackets(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.channelRead(channelHandlerContext, o);
    }

    @Override
    public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {
        try {
            Packet packet = new Packet(PacketDirection.OUTBOUND, o);
            data.packetManager.handlePackets(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.write(channelHandlerContext, o, channelPromise);
    }
}
