package me.bigfatman.joe.check.impl.combat.autoclicker;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.arm.SwingCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.utils.MathUtils;

@CheckManifest(name = "AutoClicker A", type = Check.CheckType.COMBAT)
public class AutoClickerA extends SwingCheck {

    public AutoClickerA(PlayerData data) {
        super(data);

        setSize(50);
    }

    @Override
    public void swingCheck(Object o) {
        double cps = MathUtils.getCps(delays);

        if (cps > 20) {
            flag("CPS+=" + cps);
        }
    }
}
