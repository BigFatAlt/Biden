package me.bigfatman.joe.check.type.arm;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInArmAnimation;

public abstract class SwingCheck extends Check<Object> {

    public SwingCheck(PlayerData data) {
        super(data);
    }

    @Override
    public void handlePacket(Object object) {
        if (object instanceof WrappedPlayInArmAnimation) {
            swingCheck(object);
        }
    }

    public abstract void swingCheck(Object o);

}
