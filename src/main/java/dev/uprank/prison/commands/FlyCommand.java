package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlyCommand implements CommandExecutor {

    private final Prison plugin;
    private final List<Player> isFlying;

    public FlyCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("fly").setExecutor(this);

        this.isFlying = new ArrayList<>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player player)) return false;

        if (this.isFlying.contains(player)) {
            player.setAllowFlight(false);
            player.sendMessage(Prison.PREFIX + "§7You have §cdisabled §7the flight mode!");
            this.isFlying.remove(player);
        } else {
            player.setAllowFlight(true);
            player.sendMessage(Prison.PREFIX + "§7You have §aactivated §7the flight mode!");
            this.isFlying.add(player);
        }

        return false;
    }
}
