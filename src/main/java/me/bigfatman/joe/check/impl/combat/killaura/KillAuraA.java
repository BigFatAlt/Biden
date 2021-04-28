package me.bigfatman.joe.check.impl.combat.killaura;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.PacketCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;

@CheckManifest(name = "KillAura A", type = Check.CheckType.COMBAT)
public class KillAuraA extends PacketCheck {

    public long lastFlying;
    public int vl;

    public KillAuraA(PlayerData data) {
        super(data);
    }

    @Override
    public void handlePacket(Object o) {
        if (o instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity useEntity = (WrappedPlayInUseEntity) o;

            if (useEntity.useEntityAction.equals(PacketPlayInUseEntity.EnumEntityUseAction.ATTACK)) {
                long postAura = lastFlying - System.currentTimeMillis();

                if (postAura < 40L) {
                    if (vl++ > 3) {
                        flag("post <=" + postAura);
                    }
                } else if (vl > 0) vl--;
            }
        }

        if (o instanceof WrappedPlayInFlyingPacket) {
            lastFlying = System.currentTimeMillis();
        }
    }
}