package me.moomoo.alternativeaef.misc;

import me.moomoo.alternativeaef.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class RenderDistance implements Listener {
    private final Map<Player, Integer> renderDistancePerPlayer = new HashMap<>();
    private final Main plugin;

    public RenderDistance(Main plugin) {
        this.plugin = plugin;
    }

//    @EventHandler
//    private void onElytra(EntityToggleGlideEvent evt){
//        if(evt.getEntity() instanceof Player){
//            if(evt.isGliding()){
//                setRenderDistance(((Player) evt.getEntity()).getPlayer(), plugin.getConfig().getInt("ElytraRenderDistance"));
//            } else {
//                setRenderDistance(((Player) evt.getEntity()).getPlayer(), Bukkit.getViewDistance());
//            }
//        }
//    }
//    private void setRenderDistance(Player p, int distance) {
//        if (renderDistancePerPlayer.get(p) != null) {
//            if (renderDistancePerPlayer.get(p) != distance) {
//                p.setViewDistance(distance);
//                renderDistancePerPlayer.put(p, distance);
//            }
//        } else {
//            p.setViewDistance(distance);
//            renderDistancePerPlayer.put(p, distance);
//        }
//    }
}
