package me.bigfatman.joe.commands.api;

import org.bukkit.command.CommandSender;

public interface BaseCommand {

    public String name();
    public String permission();
    public String description();

    public void handleCommand(CommandSender commandSender,String[] args);

}
