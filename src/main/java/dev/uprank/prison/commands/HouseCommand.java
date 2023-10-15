package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import dev.uprank.prison.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class HouseCommand implements CommandExecutor {

    private final Prison plugin;

    public HouseCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("house").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player player)) return false;

        Inventory inventory = Bukkit.createInventory(null, 9, "Your house");
        inventory.addItem(new ItemBuilder(Material.BAMBOO_DOOR, 1, (byte) 0).setDisplayName("§6Teleport").setLore(List.of("§7Teleport to your house")).build());
        inventory.addItem(new ItemBuilder(Material.GOLD_INGOT, 1, (byte) 0).setDisplayName("§6Rent").setLore(List.of("§7Rent your house")).build());
        inventory.addItem(new ItemBuilder(Material.EMERALD, 1, (byte) 0).setDisplayName("§6Upgrade").setLore(List.of("§7Upgrade your house")).build());
        inventory.setItem(8, new ItemBuilder(Material.BARREL, 1, (byte) 0).setDisplayName("§6Delete").setLore(List.of("§7Delete your house")).build());
        player.openInventory(inventory);

        return false;
    }
}
