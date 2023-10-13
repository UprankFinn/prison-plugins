package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import dev.uprank.prison.scoreboard.ScoreboardUtil;
import dev.uprank.prison.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PayCommand implements TabCompleter, CommandExecutor {

    private final Prison plugin;

    public PayCommand(Prison plugin) {
        this.plugin = plugin;
        PluginCommand pluginCommand = this.plugin.getCommand("pay");
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player player)) return false;

        if (args.length == 0) {
            player.sendMessage(Prison.PREFIX + "§7usage: pay <playerName> (pay a player something)");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
            return false;
        }

        if (args.length == 2) {

            String name = args[0];
            Integer amount = Integer.valueOf(args[1]);

            Player target = Bukkit.getPlayer(name);

            if (target == null) {
                player.sendMessage(Prison.PREFIX + "§cThis player is currently not online!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
                return false;
            }

            if (name.equals(player.getName())) {
                player.sendMessage(Prison.PREFIX + "§cYou can't pay yourself!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
                return false;
            }

            player.sendMessage(Prison.PREFIX + "§7You have §6" + target.getName() + " " + amount + " §7paid over!");
            target.sendMessage(Prison.PREFIX + "§7You have §6" + amount + " §7from §6" + player.getName() + " §7received!");

            ScoreboardUtil.updateScoreboard(player, 4, "§7Balance: §6" + MathUtil.kuerzeInteger(Prison.getInstance().getPlayerEntityManager().getPlayer(player.getUniqueId()).getBalance()));
            ScoreboardUtil.updateScoreboard(target, 4, "§7Balance: §6" + MathUtil.kuerzeInteger(Prison.getInstance().getPlayerEntityManager().getPlayer(target.getUniqueId()).getBalance()));

            player.playSound(player.getLocation(), Sound.ENTITY_TURTLE_LAY_EGG, 1f, 1f);
            target.playSound(target.getLocation(), Sound.ENTITY_TURTLE_LAY_EGG, 1f, 1f);

        } else {
            player.sendMessage(Prison.PREFIX + "§7usage: pay <playerName> (pay a player something)");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        if (args.length == 1) {
            List<String> names = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach((player) -> {
                names.add(player.getName());
            });
            return names;
        }

        return null;
    }
}
