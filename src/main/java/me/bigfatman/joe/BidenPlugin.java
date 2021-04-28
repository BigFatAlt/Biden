package me.bigfatman.joe;

import org.bukkit.plugin.java.JavaPlugin;

public class BidenPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        //Starting biden
        Biden.INSTANCE.onStart(this);
    }

    @Override
    public void onDisable() {
        //Stopping biden
        Biden.INSTANCE.onStop();
    }
}
