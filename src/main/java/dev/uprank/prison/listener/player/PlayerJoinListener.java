package dev.uprank.prison.listener.player;

import com.sk89q.worldedit.entity.Player;
import dev.uprank.prison.Prison;
import dev.uprank.prison.enchantment.tool.custom.Laser;
import dev.uprank.prison.entity.player.PlayerEntity;
import dev.uprank.prison.scoreboard.ScoreboardUtil;
import dev.uprank.prison.util.MathUtil;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardTeam;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class PlayerJoinListener implements Listener {

    private final Prison plugin;

    public PlayerJoinListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(PlayerJoinEvent event) {

        this.plugin.getPlayerEntityManager().createPlayer(new PlayerEntity(event.getPlayer().getUniqueId(), event.getPlayer().getName(),
                1000, 0, 0, "A", 0, 0, Arrays.asList("A")));

        event.getPlayer().sendTitle("§eWelcome to", "§6our Prison server");
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

        event.setJoinMessage(null);

        Map<Integer, String> scores = new HashMap<>();
        scores.put(8, ChatColor.RED.toString());
        scores.put(7, "§7Current Rank: §6" + this.plugin.getPlayerEntityManager().getPlayer(event.getPlayer().getUniqueId()).getMine());
        scores.put(6, "§7Next Rank: §6" + (!this.plugin.getPlayerEntityManager().getPlayer(event.getPlayer().getUniqueId()).getMine().equalsIgnoreCase("Z") ? Character.toString((char) (this.plugin.getPlayerEntityManager().getPlayer(event.getPlayer().getUniqueId()).getMine().charAt(0) + 1)) : "A"));
        scores.put(5, ChatColor.RED.toString());
        scores.put(4, "§7Balance: §6" + MathUtil.kuerzeInteger(this.plugin.getPlayerEntityManager().getPlayer(event.getPlayer().getUniqueId()).getBalance()));
        scores.put(3, "§7Gems: §6" + MathUtil.kuerzeInteger(this.plugin.getPlayerEntityManager().getPlayer(event.getPlayer().getUniqueId()).getGems()));
        scores.put(2, ChatColor.RED.toString());
        scores.put(1, "§7Blocks Mined: §6" + MathUtil.kuerzeInteger(this.plugin.getPlayerEntityManager().getPlayer(event.getPlayer().getUniqueId()).getMinedBlocks()));
        scores.put(0, ChatColor.BLACK.toString());

        ScoreboardUtil.createScoreboard(event.getPlayer(), "§cuprank.dev", scores);

    }
}
