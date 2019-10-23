package me.darkeyedragon.sugarcanetracker.event;

import me.darkeyedragon.sugarcanetracker.database.Controller;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerBreakSugarcane implements Listener {

    private final Controller controller;

    public PlayerBreakSugarcane(Controller controller) {
        this.controller = controller;
    }

    @EventHandler
    public void playerLeftClick(PlayerInteractEvent event){
        if(event.getAction() == Action.LEFT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.SUGAR_CANE_BLOCK){
            //controller.createDb();
        }
    }

}
