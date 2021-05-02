package me.bigfatman.joe.check.impl.combat.killaura;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.PacketCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInUseEntity;

@CheckManifest(name = "KillAura B", type = Check.CheckType.COMBAT)
public class KillAuraB extends PacketCheck {

    public int vl;

    public KillAuraB(PlayerData data) {
        super(data);
    }

    @Override
    public void handlePacket(Object o) {
        if (o instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity useEntity = (WrappedPlayInUseEntity) o;

            if (useEntity.action.equals(WrappedPlayInUseEntity.Action.ATTACK)) {
                if (data.interactData.digging) {
                    if (vl++ > 4) {
                        flag();
                    }
                } else if (vl > 0) vl--;
            }
        }
    }
}
