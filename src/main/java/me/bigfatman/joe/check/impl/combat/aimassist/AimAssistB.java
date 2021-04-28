package me.bigfatman.joe.check.impl.combat.aimassist;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.flying.RotationCheck;
import me.bigfatman.joe.data.PlayerData;

@CheckManifest(name = "AimAssist B", type = Check.CheckType.COMBAT)
public class AimAssistB extends RotationCheck {

    public double lastDeltaYaw;

    public AimAssistB(PlayerData data) {
        super(data);
    }

    @Override
    public void rotationCheck(Object object) {
        double deltaYaw = Math.abs(data.locationData.currentYaw - data.locationData.pastYaw);
        double acceleration = Math.abs(lastDeltaYaw - deltaYaw);

        if (data.interactData.hitTicks <= 5) {
        }

        this.lastDeltaYaw = deltaYaw;

    }
}
