package dev.uprank.prison.listener.block;

import dev.uprank.prison.Prison;
import dev.uprank.prison.entity.mine.MineEntity;
import dev.uprank.prison.util.InventoryUtil;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

    private final Prison plugin;

    public BlockBreakListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(BlockBreakEvent event) {
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            if (this.plugin.getMineEntityManager().getMine(event.getBlock().getLocation()) == null && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                event.setCancelled(true);
            } else if (this.plugin.getMineEntityManager().getMine(event.getBlock().getLocation()) != null && event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {

                event.setDropItems(false);

                if (!(InventoryUtil.isInventoryFull(event.getPlayer()))) {
                    event.getPlayer().getInventory().addItem(new ItemStack(event.getBlock().getType(), 1));
                } else {
                    event.getPlayer().sendTitle("§cWARNING", "§cYour Inventory is full");
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
                }

                MineEntity mineEntity = this.plugin.getMineEntityManager().getMine(event.getBlock().getLocation());
                mineEntity.calculateReset();

            }
        }
    }
}
