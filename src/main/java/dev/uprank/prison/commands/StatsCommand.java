package dev.uprank.prison.commands;

import dev.uprank.prison.Prison;
import dev.uprank.prison.entity.player.PlayerEntity;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

    private final Prison plugin;

    public StatsCommand(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("stats").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player player)) return false;

        if (this.plugin.getPlayerEntityManager().getPlayer(player.getUniqueId()) == null) {
            player.sendMessage(Prison.PREFIX + "§cAn unexpected error has occurred. Please report it to a staff member!");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1f, 1f);
            return false;
        }

        PlayerEntity playerEntity = this.plugin.getPlayerEntityManager().getPlayer(player.getUniqueId());

        player.sendMessage(Prison.PREFIX + "§7Your statistics:");
        player.sendMessage(Prison.PREFIX + "§7Your Balance: §6" + playerEntity.getBalance());
        player.sendMessage(Prison.PREFIX + "§7Your Gems: §6" + playerEntity.getGems());
        player.sendMessage(Prison.PREFIX + "§7Your mined Blocks: §6" + playerEntity.getMinedBlocks());
        player.sendMessage(Prison.PREFIX + "§7Your active Mine: §6" + playerEntity.getMine());


        return false;
    }
}
