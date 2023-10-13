package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineCommand implements CommandExecutor {

    private final Prison plugin;

    public MineCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("mine").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player player)) {
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(Prison.PREFIX + "§7Please use: §6/mine <mine(A,B,C,...)>");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
        } else if (args.length == 1) {
            String warp = args[0];

            if (this.plugin.getMineEntityManager().getMines().get(warp) == null) {
                player.sendMessage(Prison.PREFIX + "§cThis Mine does not exist!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
                return false;
            }

            if (!(this.plugin.getPlayerEntityManager().getPlayer(player.getUniqueId()).getAllowedMines().contains(warp))) {
                player.sendMessage(Prison.PREFIX + "§cYou are not allowed to enter this mine!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
                return false;
            }

            player.teleport(this.plugin.getMineEntityManager().getMine(warp).getLocation().toLocation());
            player.sendMessage(Prison.PREFIX + "§7You have successfully teleported to the Mine §6" + warp);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

        }

        return false;
    }
}
