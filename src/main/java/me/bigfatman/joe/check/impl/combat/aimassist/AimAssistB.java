package me.bigfatman.joe.check.impl.combat.aimassist;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.flying.RotationCheck;
import me.bigfatman.joe.data.PlayerData;

@CheckManifest(name = "AimAssist B", type = Check.CheckType.COMBAT)
public class AimAssistB extends RotationCheck {

    public double lastDeltaYaw, lastDeltaPitch;
    public int vl;

    public AimAssistB(PlayerData data) {
        super(data);
    }

    /*
     I thought of this check while i was sitting in maths class
     i divide the yawAcceleration by pi while checking for small yaw and pitch movements
     the biggest number i hit is 50.35 so i check if its above that
     Only false flag is Optifine and cinimatic but when i add a check for that its all good
     no idea if this is good so its just a concept and experimental.
     */

    @Override
    public void rotationCheck(Object object) {
        double deltaYaw = Math.abs(data.locationData.currentYaw - data.locationData.pastYaw);
        double deltaPitch = Math.abs(data.locationData.currentPitch - data.locationData.pastPitch);

        double pitchAcceleration = Math.abs(lastDeltaPitch - deltaPitch);
        double yawAcceleration = Math.abs(lastDeltaYaw - deltaYaw);

        double PITest = Math.abs(Math.PI / yawAcceleration);

        if (data.interactData.hitTicks <= 5) {
            if (deltaYaw > 0.1 && yawAcceleration < 0.995 && deltaPitch > 0.1 && pitchAcceleration < 0.995) {
                if (PITest > 50.4) {
                    vl++;
                   if (vl > 20) {
                       flag("PI+=" + PITest);
                   }
                } else if (vl > 0) vl--;
            }
        }

        this.lastDeltaPitch = deltaPitch;
        this.lastDeltaYaw = deltaYaw;
    }
}
