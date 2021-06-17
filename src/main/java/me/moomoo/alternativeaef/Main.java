package me.moomoo.alternativeaef;

import me.moomoo.alternativeaef.patches.Elytra;
import me.moomoo.alternativeaef.patches.ProtocolLib;
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
                new Elytra(this)
        );
        ProtocolLib.protocolLibWrapper(this);
        log.info("Registering events finished");
        log.info("Registering commands");
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
