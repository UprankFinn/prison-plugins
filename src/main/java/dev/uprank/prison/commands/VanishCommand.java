package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements CommandExecutor {

    private final Prison plugin;
    private final List<Player> isVanish;

    public VanishCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("vanish").setExecutor(this);

        this.isVanish = new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player player)) return false;

        if (this.isVanish.contains(player)) {

            Bukkit.getOnlinePlayers().forEach((players) -> {
                players.showPlayer(this.plugin, player);
            });

            player.sendMessage(Prison.PREFIX + "§7You have §cdisabled §7the vanish mode!");
            this.isVanish.remove(player);
        } else {
            Bukkit.getOnlinePlayers().forEach((players) -> {
                if (!(this.isVanish.contains(players))) {
                    players.hidePlayer(this.plugin, player);
                }
            });

            player.sendMessage(Prison.PREFIX + "§7You have §aactivated §7the vanish mode!");
            this.isVanish.add(player);
        }

        return false;
    }
}
