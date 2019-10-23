package me.darkeyedragon.sugarcanetracker.event;

import me.darkeyedragon.sugarcanetracker.database.Controller;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.sql.SQLException;
import java.util.UUID;

public class PlayerBreakSugarcane implements Listener {

    private final Controller controller;

    public PlayerBreakSugarcane(Controller controller) {
        this.controller = controller;
    }

    @EventHandler
    public void playerLeftClick(PlayerInteractEvent event){
        if(event.getAction() == Action.LEFT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.SUGAR_CANE_BLOCK){
            try {
                UUID uuid = event.getPlayer().getUniqueId();
                int sugarcaneCount = controller.getSugarcaneCount(uuid);
                controller.updateSugarcanecount(uuid, sugarcaneCount + 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
