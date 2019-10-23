package me.darkeyedragon.sugarcanetracker.event;

import me.darkeyedragon.sugarcanetracker.Sugarcanetracker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class UserJoinEvent implements Listener {

    private final Sugarcanetracker plugin;

    public UserJoinEvent(Sugarcanetracker plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        try {
            plugin.getController().addPlayer(event.getPlayer().getUniqueId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
