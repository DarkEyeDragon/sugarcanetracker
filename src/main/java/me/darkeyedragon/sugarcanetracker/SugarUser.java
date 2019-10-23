package me.darkeyedragon.sugarcanetracker;

import org.bukkit.Bukkit;

import java.util.UUID;

public class SugarUser {

    private int sugarcaneBroken;
    private UUID uuid;

    public SugarUser(UUID uuid, int sugarcaneBroken) {
        this.sugarcaneBroken = sugarcaneBroken;
        this.uuid = uuid;
    }

    public int getSugarcaneBroken() {
        return sugarcaneBroken;
    }

    public void setSugarcaneBroken(int sugarcaneBroken) {
        this.sugarcaneBroken = sugarcaneBroken;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }
}
