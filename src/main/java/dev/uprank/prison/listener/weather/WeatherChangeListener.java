package dev.uprank.prison.listener.weather;

import dev.uprank.prison.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {

    private final Prison plugin;

    public WeatherChangeListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
