package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    private final Prison plugin;

    public FeedCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("feed").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player player)) return false;

        player.setFoodLevel(20);
        player.sendMessage(Prison.PREFIX + "§7You've successfully fed yourself.");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

        return false;
    }
}
