package dev.uprank.prison.listener.block;

import dev.uprank.prison.Prison;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final Prison plugin;

    public BlockPlaceListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(BlockPlaceEvent event) {
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            if (!(event.getPlayer().getGameMode().equals(GameMode.CREATIVE))) {
                if (this.plugin.getMineEntityManager().getMine(event.getBlock().getLocation()) == null) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
