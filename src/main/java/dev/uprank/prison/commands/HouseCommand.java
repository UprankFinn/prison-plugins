package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HouseCommand implements CommandExecutor {

    private final Prison plugin;

    public HouseCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("house").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        return false;
    }
}
