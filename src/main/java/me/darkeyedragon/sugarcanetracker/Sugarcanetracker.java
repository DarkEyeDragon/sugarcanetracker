package me.darkeyedragon.sugarcanetracker;

import me.darkeyedragon.sugarcanetracker.database.Controller;
import me.darkeyedragon.sugarcanetracker.event.PlayerBreakSugarcane;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;

public final class Sugarcanetracker extends JavaPlugin {

    private File pluginPath = getDataFolder();

    private Controller controller;

    @Override
    public void onEnable() {
        pluginPath = this.getDataFolder();
        controller = new Controller(this);
        try {
            controller.createDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerBreakSugarcane(controller), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public File getPluginPath() {
        return pluginPath;
    }

    public Controller getController() {
        return controller;
    }
}
