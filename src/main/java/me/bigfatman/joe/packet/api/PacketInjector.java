package me.bigfatman.joe.packet.api;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import me.bigfatman.joe.data.PlayerData;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PacketInjector {

    public ExecutorService packetThread = Executors.newCachedThreadPool();


    public void injectPlayer(PlayerData data) {
        try {

            PlayerConnection playerConnection = ((CraftPlayer)data.getBukkitPlayer()).getHandle().playerConnection;

            Channel channel = playerConnection.networkManager.channel;
            ChannelPipeline channelPipeline = channel.pipeline();

            packetThread.execute(() -> {
                channelPipeline.addBefore("packet_handler", "biden_handler", new PacketHandler(data));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ejectPlayer(PlayerData data) {
        try {
            PlayerConnection playerConnection = ((CraftPlayer)data.getBukkitPlayer()).getHandle().playerConnection;

            if (playerConnection != null && !playerConnection.isDisconnected()) {
                Channel channel = playerConnection.networkManager.channel;
                ChannelPipeline pipeline = channel.pipeline();

                packetThread.execute(() -> {
                    channel.eventLoop().execute(() -> {
                        if (pipeline.get("biden_handler") != null)
                            pipeline.remove("biden_handler");
                    });
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
