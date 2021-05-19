package me.bigfatman.joe.check.impl.combat.aimassist;

import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.flying.RotationCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.utils.MathUtils;

@CheckManifest(name = "AimAssist F")
public class AimAssistF extends RotationCheck {

    public double lastDeltaYaw, lastDeltaPitch;
    public int vl;

    public AimAssistF(PlayerData data) {
        super(data);
    }

    /*
     * This check i made at 2am so basically im checking the gcd along with the hypto
     * no idea why this works but it does with some aura
     */

    @Override
    public void rotationCheck(Object object) {
        if (data.interactData.hitTicks <= 5) {
            double deltaYaw = Math.abs(data.locationData.currentYaw - data.locationData.pastYaw);
            double deltaPitch = Math.abs(data.locationData.currentPitch - data.locationData.pastPitch);

            double hypot = MathUtils.hypot(deltaYaw - lastDeltaYaw);
            long gcd = (long) MathUtils.gcd(deltaPitch * 16777216L, lastDeltaPitch * 16777216L);

            if (deltaYaw > 1.d && deltaPitch > 1.d) {
                if (hypot < 0.05 && gcd < 32) {
                    if (vl++ > 15) {
                        flag();
                    }
                } else if (vl > 0) vl--;
            }

            this.lastDeltaYaw = deltaYaw;
            this.lastDeltaPitch = deltaPitch;
        }

    }
}
