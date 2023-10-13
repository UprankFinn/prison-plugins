package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import dev.uprank.prison.util.MathUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BalanceCommand implements CommandExecutor {

    private final Prison plugin;

    public BalanceCommand(Prison plugin) {
        this.plugin = plugin;
        PluginCommand pluginCommand = this.plugin.getCommand("balance");
        pluginCommand.setExecutor(this);
        pluginCommand.setAliases(Arrays.asList("money", "geld"));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player player)) return false;

        player.sendMessage(Prison.PREFIX + "§7Your currently balance: §6" + MathUtil.kuerzeInteger(this.plugin.getPlayerEntityManager().getPlayer(player.getUniqueId()).getBalance()) + " §7(§6" + this.plugin.getPlayerEntityManager().getPlayer(player.getUniqueId()).getBalance() + "§7).");

        return false;
    }
}
