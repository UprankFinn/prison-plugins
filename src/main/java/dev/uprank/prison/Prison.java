package dev.uprank.prison;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.PlotSquared;
import dev.uprank.prison.clearlag.ClearLag;
import dev.uprank.prison.commands.*;
import dev.uprank.prison.crate.CrateManager;
import dev.uprank.prison.enchantment.EnchantmentLoader;
import dev.uprank.prison.entity.mine.MineEntityManager;
import dev.uprank.prison.entity.player.PlayerEntityManager;
import dev.uprank.prison.listener.block.BlockBreakListener;
import dev.uprank.prison.listener.block.BlockBurnListener;
import dev.uprank.prison.listener.block.BlockPlaceListener;
import dev.uprank.prison.listener.entity.EntityDamageByBlockListener;
import dev.uprank.prison.listener.entity.EntityDamageByEntityListener;
import dev.uprank.prison.listener.entity.EntitySpawnListener;
import dev.uprank.prison.listener.inventory.InventoryClickListener;
import dev.uprank.prison.listener.player.*;
import dev.uprank.prison.listener.projectile.ProjectileHitListener;
import dev.uprank.prison.listener.sign.SignChangeListener;
import dev.uprank.prison.listener.weather.WeatherChangeListener;
import dev.uprank.prison.util.InventoryUtil;
import dev.uprank.prison.util.RankManager;
import lombok.Getter;
import net.minecraft.EnumChatFormat;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreboardTeam;
import net.minecraft.world.scores.ScoreboardTeamBase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
import java.util.regex.Pattern;


@Getter
public class Prison extends JavaPlugin {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#[A-Fa-f0-9]{6}");

    public static final String PREFIX = "§7[§cPrison§7] §r";
    @Getter
    private static Prison instance;

    private final Scoreboard scoreboard = new Scoreboard();

    private BossBar bossBar;

    private final MongoClient mongoClient;

    private MineEntityManager mineEntityManager;
    private PlayerEntityManager playerEntityManager;
    private RankManager rankManager;

    private final CrateManager crateManager;
    private final InventoryUtil inventoryUtil;

    private PlotAPI plotAPI;

    public Prison() {
        instance = this;
        this.mongoClient = new MongoClient(new MongoClientURI(""));
        this.crateManager = new CrateManager();
        this.inventoryUtil = new InventoryUtil();
    }

    @Override
    public void onEnable() {

        this.mineEntityManager = new MineEntityManager(this);
        this.playerEntityManager = new PlayerEntityManager(this);
        this.rankManager = new RankManager();

        this.plotAPI = new PlotAPI();

        new ClearLag(this);

        EnchantmentLoader enchantmentLoader = new EnchantmentLoader(this);
        enchantmentLoader.loadEnchantments();

        this.mineEntityManager.getMines().forEach((s, mine) -> {
            mine.setVolume();
            mine.fill();
        });

        new BlockBreakListener(this);
        new BlockBurnListener(this);
        new BlockPlaceListener(this);

        new EntityDamageByBlockListener(this);
        new EntityDamageByEntityListener(this);
        new EntitySpawnListener(this);

        new InventoryClickListener(this);

        new AsyncPlayerChatListener(this);
        new PlayerGameModeChangeListener(this);
        new PlayerInteractListener(this);
        new PlayerJoinListener(this);
        new PlayerQuitListener(this);

        new ProjectileHitListener(this);

        new SignChangeListener(this);

        new WeatherChangeListener(this);

        new BalanceCommand(this);
        new CrateCommand(this);
        new EcoCommand(this);
        new FeedCommand(this);
        new FlyCommand(this);
        new GemsCommand(this);
        new HouseCommand(this);
        new MineCommand(this);
        new PayCommand(this);
        new RankupCommand(this);
        new SetupCommand(this);
        new StatsCommand(this);
        new VanishCommand(this);
        new WarpCommand(this);

    }

    @Override
    public void onDisable() {

    }

    public static Integer staticRandom(Integer minimum, Integer maximum) {
        Random random = new Random();
        return random.nextInt(maximum - minimum + 1) + minimum;
    }

}
