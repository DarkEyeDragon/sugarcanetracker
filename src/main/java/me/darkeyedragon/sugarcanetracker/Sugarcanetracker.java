package me.darkeyedragon.sugarcanetracker;

import me.darkeyedragon.sugarcanetracker.command.SugarcaneStats;
import me.darkeyedragon.sugarcanetracker.database.Controller;
import me.darkeyedragon.sugarcanetracker.event.PlayerBreakSugarcane;
import me.darkeyedragon.sugarcanetracker.event.UserJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;

public final class Sugarcanetracker extends JavaPlugin {

    private File pluginPath = getDataFolder();

    private Controller controller;
    private ConfigHandler configHandler;

    @Override
    public void onEnable() {
        getCommand("sugarcanetracker").setExecutor(new SugarcaneStats(this));
        configHandler = new ConfigHandler(this);
        this.saveDefaultConfig();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            getLogger().severe("Unable to load sqlite driver. This is needed to load the plugin.");
            this.getPluginLoader().disablePlugin(this);
        }
        pluginPath = this.getDataFolder();
        controller = new Controller(this);
        try {
            controller.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new PlayerBreakSugarcane(controller), this);
        getServer().getPluginManager().registerEvents(new UserJoinEvent(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            controller.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public File getPluginPath() {
        return pluginPath;
    }

    public Controller getController() {
        return controller;
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }
}
