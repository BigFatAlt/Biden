package me.bigfatman.joe.check.impl.combat.aimassist;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.flying.RotationCheck;
import me.bigfatman.joe.data.PlayerData;

@CheckManifest(name = "AimAssist D", type = Check.CheckType.COMBAT)
public class AimAssistD extends RotationCheck {

    public double lastDeltaYaw;
    public float suspiciousYaw;
    public int vl;

    public AimAssistD(PlayerData data) {
        super(data);
    }

     /*
      This is a basic rounded yaw check found in alot of anticheats
      yes i took it from another anticheat but whats the point in recoding
      whats already been done loads of times.
     */

    @Override
    public void rotationCheck(Object object) {
        double deltaYaw = Math.abs(data.locationData.currentYaw - data.locationData.pastYaw);

        if (data.interactData.hitTicks <= 5) {
            float yawChange = Math.abs(data.locationData.currentYaw - data.locationData.pastYaw);

            if (yawChange > 1F && Math.round(yawChange) == yawChange && yawChange % 1.5F != 0F) {
                if (yawChange == suspiciousYaw) {
                    flag("Y+=" + yawChange);
                }

                suspiciousYaw = Math.round(yawChange);
            } else {
                suspiciousYaw = 0F;
            }
        }

        this.lastDeltaYaw = deltaYaw;
    }
}
