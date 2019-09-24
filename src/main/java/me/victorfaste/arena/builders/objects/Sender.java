package me.victorfaste.arena.builders.objects;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sender {

    private CommandSender commandSender;

    public Sender(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public void sendMessage(String message) {
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void teleport(Location location) {
        if(!(commandSender instanceof Player)) return;
        ((Player) commandSender).teleport(location);
    }

    public Location getLocation() {
        if(!(commandSender instanceof Player)) return null;
        return ((Player) commandSender).getLocation();
    }

    public CommandSender getCommandSender() {
        return commandSender;
    }
}