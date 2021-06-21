package me.moomoo.alternativeaef.misc;

import me.moomoo.alternativeaef.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;

public class Commands implements CommandExecutor, Listener {
    private final Main plugin;
    private final FileConfiguration config;
    private final HashMap<Player, String> messages = new HashMap<>();

    public Commands(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("aef")) {
            if (args[0].toLowerCase().startsWith("ver")) {
                sender.sendMessage(ChatColor.GOLD + "This server is running Alternative AnarchyExploitFixes v" + plugin.getDescription().getVersion());
                return true;
            }
            if (!(sender instanceof Player) || sender.isOp()) {
                if (args[0].contains("reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.GOLD + "AnarchyExploitFixes config reloaded.");
                    return true;
                }
                if (args[0].equals("geared")) {
                    StringBuilder geared = new StringBuilder();
                    StringBuilder newPlayer = new StringBuilder();
                    Integer gearedInt = 0;
                    Integer newPlayerInt = 0;
                    for (Player c : Bukkit.getServer().getOnlinePlayers()) {
                        if (c.getInventory().getChestplate() != null || c.getInventory().getBoots() != null || c.getInventory().getHelmet() != null || c.getInventory().getLeggings() != null) {
                            geared.append(c.getName()).append(", ");
                            gearedInt++;
                        } else {
                            newPlayer.append(c.getName()).append(", ");
                            newPlayerInt++;
                        }
                    }
                    sender.sendMessage("Geared players: " + ChatColor.GOLD + geared + ChatColor.WHITE + " (" + gearedInt + ")");
                    sender.sendMessage("Naked players: " + ChatColor.GOLD + newPlayer + ChatColor.WHITE + " (" + newPlayerInt + ")");
                    sender.sendMessage("Total players: " + ChatColor.GOLD + (newPlayerInt + gearedInt));
                    return true;
                }
                if (args[0].equals("elytra")) {
                    StringBuilder flying = new StringBuilder();
                    StringBuilder notFlying = new StringBuilder();
                    Integer flyInt = 0;
                    Integer noFlyInt = 0;

                    for (Player c : Bukkit.getServer().getOnlinePlayers()) {
                        if (c.isGliding()) {
                            flying.append(c.getName()).append(", ");
                            flyInt++;
                        } else {
                            notFlying.append(c.getName()).append(", ");
                            noFlyInt++;
                        }
                    }

                    sender.sendMessage("Flying on elytra: " + ChatColor.GOLD + flying + ChatColor.WHITE + " (" + flyInt + ")");
                    sender.sendMessage("Not flying on elytra: " + ChatColor.GOLD + notFlying + ChatColor.WHITE + " (" + noFlyInt + ")");
                    sender.sendMessage("Total players: " + ChatColor.GOLD + (flyInt + noFlyInt));
                    return true;
                }
                if (args[0].equals("lag")) {
                    try {
                        Thread.sleep(1000);
                        return true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    @EventHandler
    private void onChat(AsyncPlayerChatEvent evt) {
        messages.put(evt.getPlayer(), evt.getMessage());
    }

    @EventHandler
    private void onPreProcess(PlayerCommandPreprocessEvent evt) {
        if (evt.getMessage().equals("/aef")) {
            evt.getPlayer().sendMessage(ChatColor.GOLD + "This server is running Alternative AnarchyExploitFixes v" + plugin.getDescription().getVersion());
            evt.setCancelled(true);
        }
    }
}
