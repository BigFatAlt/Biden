package me.bigfatman.joe.check.impl.combat.aimassist;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.flying.RotationCheck;
import me.bigfatman.joe.data.PlayerData;

@CheckManifest(name = "AimAssist C", type = Check.CheckType.COMBAT)
public class AimAssistC extends RotationCheck {

    public double lastDeltaPitch, lastPitchAcceleration;
    public int vl;

    public AimAssistC(PlayerData data) {
        super(data);
    }

    @Override
    public void rotationCheck(Object object) {
        double deltaPitch = Math.abs(data.locationData.currentPitch - data.locationData.pastPitch);
        double deltaYaw = Math.abs(data.locationData.currentYaw - data.locationData.pastYaw);

        double pitchAcceleration = Math.abs(deltaPitch - lastDeltaPitch);

        double PITest = Math.abs(Math.PI / pitchAcceleration);

        if (data.interactData.hitTicks <= 5) {
            if (deltaPitch > 3.d && lastDeltaPitch < 5.d && deltaYaw > 1.d) {
                if (PITest > 8.d) {
                    if (vl++ > 5) {
                        flag("PI+=" + PITest);
                    }
                } else if (vl > 0) vl--;
            }
        }

        this.lastPitchAcceleration = pitchAcceleration;
        this.lastDeltaPitch = deltaPitch;
    }
}
