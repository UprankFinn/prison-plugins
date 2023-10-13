package dev.uprank.prison.listener.entity;

import dev.uprank.prison.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

public class EntityDamageByEntityListener implements Listener {

    private final Prison plugin;

    public EntityDamageByEntityListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(EntityDamageByEntityEvent event) {
        if (!(event.getDamager().getLocation().getWorld().getName().equals("pvp"))) {
            event.setCancelled(true);
            event.setDamage(0.0);
        }
    }
}
