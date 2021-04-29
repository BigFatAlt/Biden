package me.bigfatman.joe.check.api;

import me.bigfatman.joe.Biden;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.utils.MiscUtils;

public abstract class Check<T> {

    public String name;
    public CheckType checkType;
    public boolean autoban, enabled;

    public CheckManifest checkManifest;

    public final PlayerData data;

    public float maxVl, vl;

    public Check(PlayerData data) {
        this.data = data;

        this.checkManifest = this.getClass().getAnnotation(CheckManifest.class);

        if (!this.getClass().isAnnotationPresent(CheckManifest.class)) {
            MiscUtils.printConsole("CheckManifest missing in " + getClass().getSimpleName() + ".");
        }

        this.name = checkManifest.name();
        this.checkType = checkManifest.type();
        this.maxVl = checkManifest.maxVl();
        this.autoban = checkManifest.autoban();
        this.enabled = checkManifest.enabled();
    }

    public abstract void handlePacket(T t);

    public void flag(String information) {
        vl++;

        Biden.INSTANCE.violationManager.handleViolation(this, data);
    }

    public enum CheckType {
        COMBAT,MOVEMENT,PACKET,OTHER
    }
}
