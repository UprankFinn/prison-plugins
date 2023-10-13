package dev.uprank.prison.listener.projectile;

import dev.uprank.prison.Prison;
import net.minecraft.world.level.Explosion;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {

    private final Prison plugin;

    public ProjectileHitListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(ProjectileHitEvent event) {
        if(event.getHitBlock() == null) return;

        if (event.getEntity().getType().equals(EntityType.EGG)) {
            if (this.plugin.getMineEntityManager().getMine(event.getHitBlock().getLocation()) != null) {
                System.out.println(":D");
            }
        }
    }

}
