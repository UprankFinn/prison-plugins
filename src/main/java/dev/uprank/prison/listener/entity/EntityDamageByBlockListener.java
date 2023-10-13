package dev.uprank.prison.listener.entity;

import dev.uprank.prison.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

public class EntityDamageByBlockListener implements Listener {

    private final Prison plugin;

    public EntityDamageByBlockListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(EntityDamageByBlockEvent event) {
        if (Objects.requireNonNull(event.getDamager().getLocation().getWorld()).getName().startsWith("prison") && Objects.requireNonNull(event.getEntity().getLocation().getWorld()).getName().startsWith("prison")) {
            event.setCancelled(true);
            event.setDamage(0.0);
        }
    }
}
