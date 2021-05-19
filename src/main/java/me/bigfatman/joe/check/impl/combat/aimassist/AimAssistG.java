package me.bigfatman.joe.check.impl.combat.aimassist;

import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.flying.RotationCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.utils.MathUtils;

@CheckManifest(name = "AimAssist G")
public class AimAssistG extends RotationCheck {


    public double lastDeltaYaw, lastDeltaPitch;
    public int vl;

    public AimAssistG(PlayerData data) {
        super(data);
    }

    /*
     * Checks for small accleration rotations while the deltaYaw is big
     * EXPERIMETNAL
     *
     */


    @Override
    public void rotationCheck(Object object) {
        if (data.interactData.hitTicks <= 5) {
            double deltaYaw = Math.abs(data.locationData.currentYaw - data.locationData.pastYaw);
            double deltaPitch = Math.abs(data.locationData.currentPitch - data.locationData.pastPitch);

            double acceleration = Math.abs(deltaYaw - lastDeltaYaw);

            if (deltaYaw > 1.d && deltaPitch > 1.d) {
                if (acceleration <= 0.1 && deltaYaw > 3.d) {
                    if (vl++ > 10) {
                        flag();
                    }
                } else if (vl > 0) vl--;
            }

            this.lastDeltaYaw = deltaYaw;
            this.lastDeltaPitch = deltaPitch;
        }

    }
}
