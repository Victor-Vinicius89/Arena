package me.victorfaste.arena;

import me.victorfaste.arena.commands.ArenaCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Arena extends JavaPlugin {

    public static final String CONSOLE_CANT_EXECUTE_COMMAND_MESSAGE = "§cApenas jogadores tem acesso à este comando!";


    private static Arena instance;

    public static Arena getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        register();
        PluginManager pluginManager = getServer().getPluginManager();

        File file = new File(getDataFolder() + File.separator + "config.yml"); //This will get the config file


        if (!file.exists()) {

            getConfig().addDefault("Name", "Value");


            getConfig().options().copyDefaults(true);
            saveConfig();
        } else {

            CheckConfig();
            saveConfig();
            reloadConfig();

        }
    }

    public void CheckConfig() {

        if (getConfig().get("Name") == null) {
            getConfig().set("Name", "Value");
            saveConfig();
            reloadConfig();

        }
    }

    private void register() {
        getCommand("arena").setExecutor(new ArenaCommand(this));
    }

    @Override
    public void onDisable() {

    }
}
