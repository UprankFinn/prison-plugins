package dev.uprank.prison.crate;

import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public enum CrateType {

    MINE_KEY("REWARD_KEY", "§8§l(§b§lReward Key§8§l)", ChatColor.BLUE, Material.TRIPWIRE_HOOK, Enchantment.LUCK, Maps.newHashMap()),
    VOTE_KEY("VOTE_KEY", "§8§l(§a§lVote Key§8§l)", ChatColor.GREEN, Material.TRIPWIRE_HOOK, Enchantment.LUCK, Maps.newHashMap()),
    CURRENT_KEY("CURRENT_KEY", "§8§l(§9§lCurrent Key§8§l)", ChatColor.DARK_BLUE, Material.TRIPWIRE_HOOK, Enchantment.LUCK, Maps.newHashMap()),
    OP_KEY("OP_KEY", "§8§l(§4§lOP Key§8§l)", ChatColor.DARK_RED, Material.TRIPWIRE_HOOK, Enchantment.LUCK, Maps.newHashMap());

    private final String key;
    private final String displayName;
    private final ChatColor chatColor;
    private final Material material;
    private final Enchantment enchantmentList;

    private final Map<RarityType, ItemStack> wins;

    CrateType(String key, String displayName, ChatColor chatColor, Material material, Enchantment enchantmentList, Map<RarityType, ItemStack> wins) {
        this.key = key;
        this.displayName = displayName;
        this.chatColor = chatColor;
        this.material = material;
        this.enchantmentList = enchantmentList;
        this.wins = wins;
    }

    public String getKey() {
        return key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ChatColor getColor() {
        return chatColor;
    }

    public Material getMaterial() {
        return material;
    }

    public Enchantment getEnchantmentList() {
        return enchantmentList;
    }

    public Map<RarityType, ItemStack> getWins() {
        return wins;
    }
}
