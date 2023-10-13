package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class HomeCommand implements CommandExecutor {

    private final Prison plugin;

    public HomeCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("home").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player player)) return false;

        Inventory inventory = Bukkit.createInventory(null, 9, "Home");

        return false;
    }
}
