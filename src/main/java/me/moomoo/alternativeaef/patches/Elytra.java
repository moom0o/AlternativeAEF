package me.moomoo.alternativeaef.patches;

import me.moomoo.alternativeaef.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class Elytra implements Listener {
    private final Main plugin;
    private final Map<Player, Integer> levels = new HashMap<>();

    public Elytra(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onOpen(EntityToggleGlideEvent evt) {
        if (plugin.getConfig().getBoolean("PatchPacketElytraFly")) {
            PlayerInventory i = ((Player) evt.getEntity()).getInventory();
            if (evt.getEntity() instanceof Player) {
                Player e = (Player) evt.getEntity();
                if (levels.get(e) != null) {
                    if (levels.get(e) > plugin.getConfig().getInt("MaxElytraOpensPer10Seconds")) {
                        if (i.getChestplate() != null && i.getChestplate().getType().equals(Material.ELYTRA)) {
                            ItemStack elytra = i.getChestplate();
                            i.setChestplate(null);
                            evt.getEntity().getWorld().dropItemNaturally(i.getLocation(), elytra);
                        }
                    } else {
                        levels.merge(e, 1, Integer::sum);
                        Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> levels.put(e, levels.get(e) - 1), 200L);
                    }
                } else {
                    levels.put(e, 1);
                    Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> levels.put(e, levels.get(e) - 1), 200L);
                }
            }
        }
    }
}
