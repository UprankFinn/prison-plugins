package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private final Prison plugin;

    public WarpCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("warp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player player)) {
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(Prison.PREFIX + "§7Please use: §6/warp <spawn:arena:plotworld>");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
        } else if (args.length == 1) {
            String warp = args[0];

            if (Bukkit.getWorld(warp) == null) {
                player.sendMessage(Prison.PREFIX + "§cThis warp does not exist!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                return false;
            }

            player.teleport(Bukkit.getWorld(warp).getSpawnLocation());
            player.sendMessage(Prison.PREFIX + "§7You have successfully teleported to the §6" + warp);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

        }

        return false;
    }
}
