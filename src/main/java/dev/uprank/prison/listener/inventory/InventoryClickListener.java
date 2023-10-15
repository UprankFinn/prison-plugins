package dev.uprank.prison.listener.inventory;

import com.plotsquared.core.PlotSquared;
import com.plotsquared.core.plot.Plot;
import dev.uprank.prison.Prison;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

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
            event.setCancelled(true);

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
        } else if (event.getView().getTitle().equals("Your house")) {
            event.setCancelled(true);

            List<Plot> plots = this.plugin.getPlotAPI().wrapPlayer(event.getWhoClicked().getUniqueId()).getPlots().stream().toList();
            Plot plot = plots.get(0);

            ItemStack itemStack = event.getCurrentItem();
            String itemName = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());

            if (itemName.equals("Teleport")) {

                event.getWhoClicked().teleport(new Location(Bukkit.getWorld("plotworld"), plot.getBottomAbs().getX(), plot.getBottomAbs().getY(), plot.getBottomAbs().getZ(), plot.getBottomAbs().getYaw(), plot.getBottomAbs().getPitch()));
            } else if (itemName.equals("Rent")) {
                if (plot == null) {
                    event.getWhoClicked().sendMessage(Prison.PREFIX + "§cYou don't have a house!");
                    ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
                    return;
                }
            } else if (itemName.equals("Upgrade")) {
                //TODO: DATABASE
            } else if (itemName.equals("Delete")) {
                //TODO:
            }

        }

    }

}
