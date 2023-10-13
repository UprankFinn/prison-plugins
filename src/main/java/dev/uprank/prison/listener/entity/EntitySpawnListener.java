package dev.uprank.prison.listener.entity;

import dev.uprank.prison.Prison;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

    private final Prison plugin;

    public EntitySpawnListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(EntitySpawnEvent event) {
        if (event.getEntity().getType().equals(EntityType.CHICKEN)) event.setCancelled(true);
    }

}
