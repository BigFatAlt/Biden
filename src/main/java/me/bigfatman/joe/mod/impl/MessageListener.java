package me.bigfatman.joe.mod.impl;

import me.bigfatman.joe.Biden;
import me.bigfatman.joe.mod.ModData;
import me.bigfatman.joe.mod.ModManager;
import me.bigfatman.joe.utils.MiscUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MessageListener implements PluginMessageListener {


    public final ModManager modManager;

    public MessageListener(ModManager modManager) {
        this.modManager = modManager;

        Bukkit.getMessenger().registerIncomingPluginChannel(Biden.INSTANCE.bidenPlugin, "FML|HS", this);
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        if (bytes[0] == 2) {
            ModData modData  = getModData(bytes);
            Biden.INSTANCE.modManager.modDataMap.put(player, modData);

            MiscUtils.forgeLogin(player);
        }
    }

    private ModData getModData(byte[] data)
    {
        Map<String, String> mods = new HashMap<>();

        boolean store = false;
        String tempName = null;

        for (int i = 2; i < data.length; store = !store)
        {
            int end = i + data[i] + 1;
            byte[] range = Arrays.copyOfRange(data, i + 1, end);

            String string = new String(range);

            if (store)
            {
                mods.put(tempName, string);
            }
            else
            {
                tempName = string;
            }

            i = end;
        }

        return new ModData(mods);
    }
}
