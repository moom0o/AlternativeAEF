package me.moomoo.alternativeaef;

import me.moomoo.alternativeaef.misc.Commands;
import me.moomoo.alternativeaef.patches.Burrow;
import me.moomoo.alternativeaef.patches.Elytra;
import me.moomoo.alternativeaef.patches.ProtocolLib;
import me.moomoo.alternativeaef.prevention.Redstone;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin implements Listener {
    public PluginManager pluginManager;

    @Override
    public void onEnable() {
        Logger log = getLogger();
        pluginManager = getServer().getPluginManager();
        log.info("Registering events");
        register(
                new Elytra(this), new Burrow(this),
                new Commands(this), new Redstone(this)
        );
        ProtocolLib.protocolLibWrapper(this);
        log.info("Registering events finished");
        log.info("Registering commands");
        getCommand("aef").setExecutor(new Commands(this));
        log.info("Registering commands finished");
        saveDefaultConfig();
        log.info("[ENABLED] Alternative AnarchyExploitFixes - Made by moomoo");
    }

    private void register(Listener... list) {
        pluginManager.registerEvents(this, this);
        for (Listener listener : list) {
            pluginManager.registerEvents(listener, this);
        }
    }
}
