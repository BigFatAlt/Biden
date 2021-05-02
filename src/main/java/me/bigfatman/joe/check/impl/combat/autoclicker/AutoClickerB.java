package me.bigfatman.joe.check.impl.combat.autoclicker;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.arm.SwingCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.utils.MathUtils;

@CheckManifest(name = "AutoClicker B", type = Check.CheckType.COMBAT)
public class AutoClickerB extends SwingCheck {

    public int vl;

    public AutoClickerB(PlayerData data) {
        super(data);

        setSize(100);
    }

    /*
     * This check checks the standard Deviation of the players swing
     * a normal player swing is above 0.45 so we check if its below that
     */

    @Override
    public void swingCheck(Object o) {
        double standardDeviation = MathUtils.getStandardDeviation(delays);

        if (standardDeviation < 0.45) {
            if (vl++ > 5) {
                flag("STD+=" + standardDeviation);
            }
        } else if (vl > 0) vl--;
    }
}
