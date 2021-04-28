package me.bigfatman.joe.commands.api;

import me.bigfatman.joe.commands.impl.Alerts;
import me.bigfatman.joe.utils.Colour;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {

    public List<BaseCommand> commands = new ArrayList<>();

    public CommandManager() {
        commands.add(new Alerts());
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String str, String[] args) {
        if (args.length > 0) {
            for (int i = 0; i  < commands.size(); i++) {
                BaseCommand baseCommand = commands.get(i);

                if (!commandSender.hasPermission(baseCommand.permission())) {
                    commandSender.sendMessage(Colour.translateColour("&cYou do not have enough permissions to execute this command."));
                    return true;
                }

                if (args[0].equalsIgnoreCase(baseCommand.name())) {
                    baseCommand.handleCommand(commandSender, args);
                    return true;
                }
                return true;
            }
            return true;
        } else helpMessage(commandSender);
        return false;
    }

    public void helpMessage(CommandSender commandSender) {
        commandSender.sendMessage(Colour.translateColour(""));
        commandSender.sendMessage(Colour.translateColour("&7* &b&lBiden &3[AntiCheat] &7* "));
        commandSender.sendMessage(Colour.translateColour(""));
        for (int i = 0; i < commands.size(); i++) {
            if (!commandSender.hasPermission(commands.get(i).permission())) return;
            commandSender.sendMessage(Colour.translateColour("&7* &b/biden " + commands.get(i).name() + " &7- " + commands.get(i).description()));
        }
        commandSender.sendMessage("");
    }
}
