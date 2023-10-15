package dev.uprank.prison.listener.block;

import dev.uprank.prison.Prison;
import dev.uprank.prison.entity.mine.MineEntity;
import dev.uprank.prison.scoreboard.ScoreboardUtil;
import dev.uprank.prison.util.InventoryUtil;
import dev.uprank.prison.util.MathUtil;
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
                event.getPlayer().giveExp(event.getExpToDrop());
                event.setExpToDrop(0);

                if (!(InventoryUtil.isInventoryFull(event.getPlayer()))) {
                    event.getPlayer().getInventory().addItem(new ItemStack(event.getBlock().getType(), 1));
                } else {
                    event.getPlayer().sendTitle("§cWARNING", "§cYour Inventory is full");
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
                }

                this.plugin.getPlayerEntityManager().getPlayer(event.getPlayer().getUniqueId()).addMinedBlock(1);
                ScoreboardUtil.updateScoreboard(event.getPlayer(), 1, "§7Blocks Mined: §6" + MathUtil.kuerzeInteger(this.plugin.getPlayerEntityManager().getPlayer(event.getPlayer().getUniqueId()).getMinedBlocks()));

                MineEntity mineEntity = this.plugin.getMineEntityManager().getMine(event.getBlock().getLocation());
                mineEntity.calculateReset();

            }
        }
    }
}
