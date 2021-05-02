package me.bigfatman.joe.check.impl.combat.autoclicker;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.arm.SwingCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.utils.MathUtils;

@CheckManifest(name = "AutoClicker D", type = Check.CheckType.COMBAT)
public class AutoClickerD extends SwingCheck {

    public int vl;

    public AutoClickerD(PlayerData data) {
        super(data);

        setSize(50);
    }

    @Override
    public void swingCheck(Object o) {
        double average = MathUtils.getAverage(delays);

        if (average < 1.5) {
            if (vl++ > 5) {
                flag("A+=" + average);
            }
        } else if (vl > 0) vl--;
    }
}
