package me.bigfatman.joe.check.impl.combat.aimassist;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.flying.RotationCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.utils.MathUtils;

@CheckManifest(name = "AimAssist E", type = Check.CheckType.COMBAT)
public class AimAssistE extends RotationCheck {

    public double lastDeltaPitch;
    public int vl;

    public AimAssistE(PlayerData data) {
        super(data);
    }

    /*
     * This is a GCD check i put together comparing the GCD to the deltaPitch
     * We check for a low GCD and a high pitch also checking the same with the last pitch
     * With these values its invalid roations so the play is cheating
     *
     * Might false flag
     */

    @Override
    public void rotationCheck(Object object) {
        double deltaPitch = Math.abs(data.locationData.currentPitch - data.locationData.pastPitch);

        if (data.interactData.hitTicks <= 5) {
            double GCD = MathUtils.gcd(deltaPitch, lastDeltaPitch);
            if (GCD < 0.005 && deltaPitch > 1.d && lastDeltaPitch > 1.d) {
                if (vl++ > 20) {
                    flag("GCD-=" + GCD + " DP+=" + deltaPitch);
                }
            } else if (vl > 0) vl--;
        }


        this.lastDeltaPitch = deltaPitch;
    }
}
