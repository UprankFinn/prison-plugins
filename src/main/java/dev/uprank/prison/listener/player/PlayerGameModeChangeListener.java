package dev.uprank.prison.listener.player;

import dev.uprank.prison.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerGameModeChangeListener implements Listener {

    private final Prison plugin;

    public PlayerGameModeChangeListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(PlayerGameModeChangeEvent event) {
        event.getPlayer().sendMessage(Prison.PREFIX + "§7You have changed your gamemode to §c" + event.getNewGameMode().name() + "!");
    }

}
