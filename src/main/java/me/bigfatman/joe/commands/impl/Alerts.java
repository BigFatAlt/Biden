package me.bigfatman.joe.commands.impl;

import me.bigfatman.joe.Biden;
import me.bigfatman.joe.commands.api.BaseCommand;
import me.bigfatman.joe.data.PlayerData;
import me.bigfatman.joe.utils.Colour;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Alerts implements BaseCommand {

    @Override
    public String name() {
        return "alerts";
    }

    @Override
    public String permission() {
        return "biden.anticheat.alerts";
    }

    @Override
    public String description() {
        return "Enabled/Disable alerts";
    }

    @Override
    public void handleCommand(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        PlayerData data = Biden.INSTANCE.dataManager.find(player);

        data.toggledAlerts = !data.toggledAlerts;
        data.getBukkitPlayer().sendMessage(Colour.translateColour(data.toggledAlerts ? "&aYou have toggled alerts." : "&cYou have disabled alerts."));
    }
}
