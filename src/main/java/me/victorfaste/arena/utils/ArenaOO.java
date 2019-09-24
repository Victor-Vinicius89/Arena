package me.victorfaste.arena.utils;

import me.victorfaste.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ArenaOO {

    private static ArenaOO instance;
    private Arena arena;
    private Location arenaLocation;
    private FileConfiguration config;
    private Location arenaLeave;

    public ArenaOO(FileConfiguration config) {
        this.config = config;
        this.refreshArenaLocation();
    }


    public void refreshArenaLocation() {
        this.arenaLocation = new Location(
                Bukkit.getWorld(config.getString("Warps." + "arena" + ".World")),
                config.getDouble("Warps." + "arena" + ".X"),
                config.getDouble("Warps." + "arena" + ".Y"),
                config.getDouble("Warps." + "arena" + ".Z"),
                (float) config.getDouble("Warps." + "arena" + ".Yaw"),
                (float) config.getDouble("Warps." + "arena" + ".Pitch"));
        this.arenaLeave = new Location(
                Bukkit.getWorld(config.getString("Warps." + "leave" + ".World")),
                config.getDouble("Warps." + "leave" + ".X"),
                config.getDouble("Warps." + "leave" + ".Y"),
                config.getDouble("Warps." + "leave" + ".Z"),
                (float) config.getDouble("Warps." + "leave" + ".Yaw"),
                (float) config.getDouble("Warps." + "leave" + ".Pitch"));

    }

    public void warpLeave(Player player, Location location) {
        if (arena.getConfig().getConfigurationSection("Warps." + "leave") == null) {
            this.arenaLocation = location;
            config.set("Warps." + "leave" + ".World", player.getWorld().getName());
            config.set("Warps." + "leave" + ".X", location.getX());
            config.set("Warps." + "leave" + ".Y", location.getY());
            config.set("Warps." + "leave" + ".Z", location.getZ());
            config.set("Warps." + "leave" + ".Yaw", location.getYaw());
            config.set("Warps." + "leave" + ".Pitch", location.getPitch());
            Arena.getInstance().saveConfig();
            player.sendMessage(ChatColor.AQUA + "Saida setada.");
        }
    }

    public void createWarp(Player player, Location location) {
        if (arena.getConfig().getConfigurationSection("Warps." + "arena") == null) {
            this.arenaLocation = location;
            config.set("Warps." + "arena" + ".World", player.getWorld().getName());
            config.set("Warps." + "arena" + ".X", location.getX());
            config.set("Warps." + "arena" + ".Y", location.getY());
            config.set("Warps." + "arena" + ".Z", location.getZ());
            config.set("Warps." + "arena" + ".Yaw", location.getYaw());
            config.set("Warps." + "arena" + ".Pitch", location.getPitch());
            Arena.getInstance().saveConfig();
            player.sendMessage(ChatColor.AQUA + "Entrada setada.");
        }
    }

    public void delEntrada(Player player) {
        if (config.contains("Warps." + "arena")) {
            config.set("Warps." + "arena", null);
            Arena.getInstance().saveConfig();
            player.sendMessage(ChatColor.AQUA + "Você deletou a entrada.");
            return;
        }
    }

    public void delLeave(Player player) {
        if (config.contains("Warps." + "leave")) {
            config.set("Warps." + "leave", null);
            Arena.getInstance().saveConfig();
            player.sendMessage(ChatColor.AQUA + "Você deletou a saida.");
            return;
        }
    }

    public void entryLeave(Player player) {
        player.teleport(arenaLeave);
        player.sendMessage(ChatColor.AQUA + "Levado para fora da arena.");
    }

    public void entryWarp(Player player) {
        player.teleport(arenaLocation);
        player.sendMessage(ChatColor.AQUA + "Você foi teletransportado para arena.");

    }
}
