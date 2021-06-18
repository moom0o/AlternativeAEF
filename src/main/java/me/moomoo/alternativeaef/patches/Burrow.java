package me.moomoo.alternativeaef.patches;

import me.moomoo.alternativeaef.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Burrow implements Listener {
    private final Main plugin;

    public Burrow(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onMove(PlayerMoveEvent evt) {
        if (plugin.getConfig().getBoolean("PreventBurrow")) {
            Location l = evt.getPlayer().getLocation();
            int x = l.getBlockX();
            int y = l.getBlockY();
            int z = l.getBlockZ();
            Material b = evt.getPlayer().getLocation().getWorld().getBlockAt(x, y, z).getType();
            if (b != Material.AIR && b.isOccluding()) {
                evt.getPlayer().damage(plugin.getConfig().getInt("BurrowDamageWhenMoving"));
            }
        }
    }
}
