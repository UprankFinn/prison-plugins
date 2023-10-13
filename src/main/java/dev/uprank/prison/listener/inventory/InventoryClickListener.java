package dev.uprank.prison.listener.inventory;

import dev.uprank.prison.Prison;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private final Prison plugin;

    public InventoryClickListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(InventoryClickEvent event) {
        if (event.getAction() == null) return;
        if (event.getClick() == null) return;
        if (event.getInventory() == null) return;
        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;

        if (event.getView().getTitle().equals("Mines")) {

            ItemStack itemStack = event.getCurrentItem();
            String mine = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());

            if (this.plugin.getMineEntityManager().getMine(mine) == null) {
                event.getWhoClicked().sendMessage(Prison.PREFIX + "§cAn unexpected error has occurred. Please report it to a staff member!");
                ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
                return;
            }

            if (!(this.plugin.getPlayerEntityManager().getPlayer(((Player) event.getWhoClicked()).getUniqueId()).getMines().containsKey(mine))) {
                this.plugin.getRankManager().rankUp(this.plugin.getPlayerEntityManager().getPlayer(((Player) event.getWhoClicked()).getUniqueId()));
                event.getWhoClicked().closeInventory();
            } else {
                event.getWhoClicked().sendMessage(Prison.PREFIX + "§cYou have already unlocked this mine!");
                ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
                event.getWhoClicked().closeInventory();
            }

            event.setCancelled(true);
        }

    }

}
