package me.bigfatman.joe.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MiscUtils {

    public static void bidenMessage(String message) {
        Bukkit.getOnlinePlayers().stream().filter(player -> player.isOp() || player.hasPermission("biden.admin"))
                .forEach(player -> player.sendMessage(Colour.translateColour("&b[Biden] &f" + message)));
    }

    public static void forgeLogin(Player target) {
        Bukkit.getOnlinePlayers().stream().filter(player -> player.isOp() || player.hasPermission("biden.admin"))
                .forEach(player -> player.sendMessage(Colour.translateColour("&b&l[AC] &3" + target.getName() + " &fhas logged in with forge.")));
    }

    public static void printConsole(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(Colour.translateColour("&4[CONSOLE] &c" + message));
    }
}
