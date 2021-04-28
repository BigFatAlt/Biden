package me.bigfatman.joe.utils;

import org.bukkit.ChatColor;

public class Colour {

    public static String translateColour(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
