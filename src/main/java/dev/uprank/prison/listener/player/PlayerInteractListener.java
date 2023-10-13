package dev.uprank.prison.listener.player;

import dev.uprank.prison.Prison;
import dev.uprank.prison.crate.Crate;
import dev.uprank.prison.util.ItemBuilder;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Egg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PlayerInteractListener implements Listener {

    private final Prison plugin;

    public PlayerInteractListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.PHYSICAL)) event.setCancelled(true);
        if (event.getPlayer() == null) return;
        if (event.getHand() == null) return;
        if (event.getClickedBlock() == null) return;

        Location location = new Location(Bukkit.getWorld("world"), 9, -43, -0);

        Block block = event.getClickedBlock();
        if (block == null) return;

        if (block.getType().equals(Material.CHEST) && block.getLocation().equals(location)) {
            ItemStack itemStack = event.getItem();
            event.setCancelled(true);

            if (itemStack == null) return;

            if (itemStack.getItemMeta().getLocalizedName() == null) {
                event.getPlayer().sendMessage(Prison.PREFIX + "§cDu hast kein Gültigen Key in der Hand!");
                return;
            }

            Crate openedCrate = this.plugin.getCrateManager().getCrate(Objects.requireNonNull(event.getPlayer().getItemInHand().getItemMeta()).getLocalizedName());

            if (openedCrate == null) {
                event.getPlayer().sendMessage(Prison.PREFIX + "§cDu hast kein Gültigen Key in der Hand!");
                return;
            }

            if (openedCrate.localizedName().equals(itemStack.getItemMeta().getLocalizedName())) {
                if (itemStack.getItemMeta().getLocalizedName().equals("MINE_KEY")) {

                    if (this.plugin.getInventoryUtil().isInventoryFull(event.getPlayer())) {
                        event.getPlayer().sendMessage(Prison.PREFIX + "§cDein Inventar ist voll!");
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                        return;
                    }

                    Integer tokens = Prison.staticRandom(5, 15);

                    //this.plugin.getPlayerManager().getPlayer(event.getPlayer().getUniqueId()).addTokens(tokens);
                    this.plugin.getInventoryUtil().remove(event.getPlayer().getInventory(), itemStack, "MINE_KEY", 1);

                    event.getPlayer().sendMessage(Prison.PREFIX + "§aDu hast ein Mine Key geöffnet und " + tokens + " Tokens bekommen!");

                    //UnestiaAPI.getInstance().getScoreboardUtil().updateScoreboard(event.getPlayer(), 8, "§e» §7Tokens: §f" + player.getTokens());

                } else {

                    if (this.plugin.getInventoryUtil().isInventoryFull(event.getPlayer())) {
                        event.getPlayer().sendMessage(Prison.PREFIX + "§cDein Inventar ist voll!");
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                        return;
                    }

                    if (openedCrate.crateType().getWins().isEmpty()) {
                        event.getPlayer().sendMessage(Prison.PREFIX + "§cEs sind derzeit keine Items für diese Crate verfügbar!");
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                        return;
                    }

                    Random random = new Random();

                    ItemStack win = openedCrate.crateType().getWins().get(random.nextInt(openedCrate.crateType().getWins().size()));

                    event.getPlayer().getInventory().addItem(new ItemBuilder(win).build());

                    this.plugin.getInventoryUtil().remove(event.getPlayer().getInventory(), itemStack, openedCrate.localizedName(), 1);
                    event.getPlayer().sendMessage(Prison.PREFIX + "§aDu hast ein " + openedCrate.crateType().getKey() + " Key geöffnet und " + win.getType().name().replace("_", " ") + " bekommen!");

                }
            }

        }

        if (event.getItem() == null) return;
        if (event.getClickedBlock() == null) return;
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getPlayer().getItemInHand().getItemMeta().getEnchants().get(Enchantment.getByKey(NamespacedKey.minecraft("xlaser"))) != null) {
                Egg egg = event.getPlayer().launchProjectile(Egg.class);
                egg.setVelocity(egg.getVelocity().multiply(2.0));
                event.setCancelled(true);

            }
        }


    }

}
