package me.darkeyedragon.sugarcanetracker.database;

import me.darkeyedragon.sugarcanetracker.Sugarcanetracker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {

    private final Sugarcanetracker plugin;
    private final String url;

    public Controller(Sugarcanetracker plugin) {
        this.plugin = plugin;
        url = "jdbc:sqlite:" + Paths.get(plugin.getPluginPath().toString(),  "data");
    }

    public void createDb() throws SQLException {
        try (Connection conn = DriverManager.getConnection(url)) {
            plugin.getLogger().info(url);
        }
    }

}
