package me.bigfatman.joe.check.impl.combat.autoclicker;

import me.bigfatman.joe.check.api.Check;
import me.bigfatman.joe.check.api.CheckManifest;
import me.bigfatman.joe.check.type.arm.SwingCheck;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.utils.MathUtils;

@CheckManifest(name = "AutoClicker C", type = Check.CheckType.COMBAT)
public class AutoClickerC extends SwingCheck {

    public int vl;

    public AutoClickerC(PlayerData data) {
        super(data);

        setSize(150);
    }

    @Override
    public void swingCheck(Object o) {
        double kurtosis = MathUtils.getKurtosis(delays);


        if (kurtosis < 0D) {
            if (vl++ > 5) {
                flag("K-=" + kurtosis);
            }
        }
    }
}
