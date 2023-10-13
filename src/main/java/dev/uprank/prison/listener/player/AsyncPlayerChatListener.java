package dev.uprank.prison.listener.player;

import dev.uprank.prison.Prison;
import dev.uprank.prison.entity.player.PlayerEntity;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    private final Prison plugin;

    public AsyncPlayerChatListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(AsyncPlayerChatEvent event) {

        if (this.plugin.getPlayerEntityManager().getPlayer(event.getPlayer().getUniqueId()) == null) {
            event.getPlayer().sendMessage(Prison.PREFIX + "§cAn unexpected error has occurred. Please report it to a staff member!");
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
            event.setCancelled(true);
            return;
        }

        PlayerEntity playerEntity = this.plugin.getPlayerEntityManager().getPlayer(event.getPlayer().getUniqueId());

        event.setFormat("§8[§6" + playerEntity.getMine() + "§8] §f" + event.getPlayer().getName() + "§7: §r" + event.getMessage().replace("%", "%%"));

    }

}
