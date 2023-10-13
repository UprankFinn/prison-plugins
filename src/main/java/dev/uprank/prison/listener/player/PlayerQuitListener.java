package dev.uprank.prison.listener.player;

import dev.uprank.prison.Prison;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final Prison plugin;

    public PlayerQuitListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(PlayerQuitEvent event) {

        event.setQuitMessage(null);

        this.plugin.getScoreboard().g().forEach((teams) -> {
            teams.g().remove(event.getPlayer().getName());
        });

    }
}
