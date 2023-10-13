package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import dev.uprank.prison.util.ItemBuilder;
import dev.uprank.prison.util.MathUtil;
import dev.uprank.prison.util.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

public class RankupCommand implements CommandExecutor {

    private final Prison plugin;

    public RankupCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("rankup").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player player)) return false;

        Inventory inventory = Bukkit.createInventory(null, 4 * 9, "Mines");
        this.plugin.getMineEntityManager().getSortedMines().forEach((s1, mine) -> {

            if (this.plugin.getPlayerEntityManager().getPlayer(player.getUniqueId()).getAllowedMines().contains(s1)) {
                inventory.addItem(new ItemBuilder(mine.getMaterial(), 1, (byte) 0).setDisplayName("§6" + mine.getName()).addEnchant(Enchantment.ARROW_FIRE, 1).addFlags(ItemFlag.HIDE_ENCHANTS).addLoreLine("§cAlready unlocked!").build());
            } else {
                inventory.addItem(new ItemBuilder(mine.getMaterial(), 1, (byte) 0).setDisplayName("§6" + mine.getName()).addLoreLine("§7Price: §6" + MathUtil.kuerzeInteger(this.plugin.getRankManager().getMinePrices().get(mine.getName()))).build());
            }
        });

        player.openInventory(inventory);

        return false;
    }
}
