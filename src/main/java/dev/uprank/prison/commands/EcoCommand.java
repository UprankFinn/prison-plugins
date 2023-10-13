package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import dev.uprank.prison.scoreboard.ScoreboardUtil;
import dev.uprank.prison.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class EcoCommand implements CommandExecutor {

    private final Prison plugin;

    public EcoCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("eco").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player player)) return false;

        if (args.length == 3) {
            String name = args[0];
            String value = args[1];
            Integer amount = Integer.valueOf(args[2]);

            Player target = Bukkit.getPlayer(name);

            if (target == null) {
                player.sendMessage(Prison.PREFIX + "§cThis player is currently not online!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
                return false;
            }

            if (value.equals("set")) {
                if (amount == null) return false;

                this.plugin.getPlayerEntityManager().getPlayer(target.getUniqueId()).setBalance(amount);
                ScoreboardUtil.updateScoreboard(target, 4, "§7Balance: §6" + MathUtil.kuerzeInteger(this.plugin.getPlayerEntityManager().getPlayer(target.getUniqueId()).getBalance()));
                player.sendMessage(Prison.PREFIX + "§7You set the money from " + "§6" + target.getName() + " to " + amount + "!");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

            }

        } else {

        }

        return false;
    }
}
