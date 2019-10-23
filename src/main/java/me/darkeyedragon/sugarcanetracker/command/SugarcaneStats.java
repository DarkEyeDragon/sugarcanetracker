package me.darkeyedragon.sugarcanetracker.command;

import me.darkeyedragon.sugarcanetracker.Sugarcanetracker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;

public class SugarcaneStats implements CommandExecutor {

    private final Sugarcanetracker plugin;

    public SugarcaneStats(Sugarcanetracker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (s.equalsIgnoreCase("sugarcanetracker") || s.equalsIgnoreCase("st") || s.equalsIgnoreCase("sugartracker") || s.equalsIgnoreCase("sugarstats")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (commandSender.hasPermission("sugarcanetracker.reload")) {

                        plugin.getConfigHandler().reload();
                        commandSender.sendMessage(ChatColor.GREEN + "Reloaded config.");
                        return true;
                    }
                } else if (args[0].equalsIgnoreCase("stats")) {
                    if (commandSender.hasPermission("sugarcanetracker.stats")) {
                        try {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(plugin.getConfigHandler().getHeader());
                            String body = plugin.getConfigHandler().getBody();
                            System.out.println(body);
                            String formattedStr = ChatColor.translateAlternateColorCodes('&', body);

                            plugin.getController().getTop().forEach((sugarUser) -> {
                                String username = sugarUser.getUsername();
                                if (username == null) username = "<invalid user>";
                                stringBuilder.append(formattedStr.replace("{player}", username)
                                        .replace("{amount}", "" + sugarUser.getSugarcaneBroken()));
                                stringBuilder.append("\n");
                            });
                            commandSender.sendMessage(ChatColor.AQUA + stringBuilder.toString());
                            commandSender.sendMessage(plugin.getConfigHandler().getFooter());
                        } catch (SQLException e) {
                            e.printStackTrace();
                            commandSender.sendMessage(ChatColor.RED + "Whoops! Something went wrong! Could not fetch sugarcane top.");
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
