package me.moomoo.alternativeaef.prevention;

import me.moomoo.alternativeaef.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.time.Instant;

public class Redstone implements Listener {
    private static Redstone instance;
    private final Main plugin;
    private final FileConfiguration config;
    private long lastGathered;

    public Redstone(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        instance = this;
    }

    @EventHandler
    public void onRedstoneEvent(BlockRedstoneEvent evt) {
        long now = Instant.now().toEpochMilli();
        double tps = Bukkit.getServer().getTPS()[0];
        if (tps < config.getDouble("Redstone")) {
            int current = evt.getOldCurrent();
            evt.setNewCurrent(current);
            if ((now - lastGathered) >= 30000) {
                lastGathered = now;
                plugin.getLogger().info("Disabled all redstone because tps is " + tps);
            }
        } else {
            int newCurrent = evt.getNewCurrent();
            evt.setNewCurrent(newCurrent);
        }
    }
}
