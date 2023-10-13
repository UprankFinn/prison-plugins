package dev.uprank.prison.listener.block;

import dev.uprank.prison.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

public class BlockBurnListener implements Listener {

    private final Prison plugin;

    public BlockBurnListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(BlockBurnEvent event) {
        if (event.getBlock().getLocation().getWorld().getName().startsWith("prison")) {
            event.setCancelled(true);
        }
    }
}
