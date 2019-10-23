package me.darkeyedragon.sugarcanetracker;

public class ConfigHandler {

    private final Sugarcanetracker plugin;


    public ConfigHandler(Sugarcanetracker plugin) {
        this.plugin = plugin;
    }

    public String getHeader() {
        return plugin.getConfig().getStringList("leaderboard").get(0) + "\n";
    }

    public String getBody() {
        return plugin.getConfig().getStringList("leaderboard").get(1) + "\n";
    }

    public String getFooter() {
        return plugin.getConfig().getStringList("leaderboard").get(2) + "\n";
    }

    public void reload() {
        plugin.reloadConfig();
    }
}
