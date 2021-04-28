package me.bigfatman.joe.check.impl.combat.aimassist;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.flying.RotationCheck;
import me.bigfatman.joe.data.PlayerData;

@CheckManifest(name = "AimAssist A", type = Check.CheckType.COMBAT)
public class AimAssistA extends RotationCheck {

    public double lastDeltaYaw;
    public int vl;

    public AimAssistA(PlayerData data) {
        super(data);
    }

    /*
     This check checks for huge rotation's on the yaw scale.
     This check does not detect a lot of auras and will probably be replaced
     */

    @Override
    public void rotationCheck(Object object) {
        double deltaYaw = Math.abs(data.locationData.currentYaw - data.locationData.pastYaw);
        double acceleration = Math.abs(lastDeltaYaw - deltaYaw);

        if (data.interactData.hitTicks <= 5) {
            if (deltaYaw >= 50.0D && acceleration <= 0.1D) {
                //We do not need a vl as its impossible to hit these values legit
                flag("DY+=" + deltaYaw + " A+=" + acceleration);
            }
        }

        this.lastDeltaYaw = deltaYaw;
    }
}
