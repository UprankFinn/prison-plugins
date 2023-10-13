package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ShopCommand implements CommandExecutor, TabCompleter {

    private final Prison plugin;

    public ShopCommand(Prison plugin) {
        this.plugin = plugin;
        PluginCommand pluginCommand = this.plugin.getCommand("shop");
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);
        pluginCommand.setAliases(Arrays.asList("store", "sh"));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player player)) return false;
        if (args.length == 0) {
            player.sendMessage(Prison.PREFIX + "§7usage: shop rent (you can rent a shop on the spawn)");
            player.sendMessage(Prison.PREFIX + "§7usage: shop upgrade (you can upgrade your shop)");
            player.sendMessage(Prison.PREFIX + "§7usage: shop cancel (you can cancel your shop)");

            player.sendMessage(Prison.PREFIX + "§7usage: shop list (you can see all shops)");
            player.sendMessage(Prison.PREFIX + "§7usage: shop visit <playerName> (you can visit a shop)");
            player.sendMessage(Prison.PREFIX + "§7usage: shop transactions (you can view your transactions)");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
            return false;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        return null;
    }
}
