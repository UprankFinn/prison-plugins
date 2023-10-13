package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import dev.uprank.prison.util.MathUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class GemsCommand implements CommandExecutor {

    private final Prison plugin;

    public GemsCommand(Prison plugin) {
        this.plugin = plugin;
        PluginCommand pluginCommand = this.plugin.getCommand("gems");
        pluginCommand.setExecutor(this);
        pluginCommand.setAliases(List.of("tokens"));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player player)) return false;

        player.sendMessage(Prison.PREFIX + "§7Your currently gems: §6" + MathUtil.kuerzeInteger(this.plugin.getPlayerEntityManager().getPlayer(player.getUniqueId()).getGems()) + " §7(§6" + this.plugin.getPlayerEntityManager().getPlayer(player.getUniqueId()).getGems() + "§7).");

        return false;
    }
}
