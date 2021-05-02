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

    /*
     * A normal player clicks under 20 so we check if the player is clicking more
     * Butterfly clicking will false this and some legit players who can click faster
     *
     *  Update: changed it to 23 cps for safety.
     */

    @Override
    public void swingCheck(Object o) {
        double cps = MathUtils.getCps(delays);

        if (cps > 23) {
            flag("CPS+=" + cps);
        }
    }
}
