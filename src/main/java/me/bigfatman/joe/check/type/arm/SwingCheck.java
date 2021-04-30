package me.bigfatman.joe.check.type.arm;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInArmAnimation;
import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;

import java.util.ArrayList;
import java.util.List;

public abstract class SwingCheck extends Check<Object> {

    public int flying;
    public int size;
    public List<Integer> delays = new ArrayList<>();


    public SwingCheck(PlayerData data) {
        super(data);
    }

    @Override
    public void handlePacket(Object object) {
        if (object instanceof WrappedPlayInArmAnimation) {
            if (flying < 10 && !data.interactData.digging) {
                delays.add(flying);
                if (delays.size() == size) {
                    swingCheck(object);
                    delays.clear();
                }
            }
            flying = 0;
        }

        if (object instanceof WrappedPlayInFlyingPacket) {
            flying++;
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public abstract void swingCheck(Object o);

}
