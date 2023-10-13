package dev.uprank.prison.reward.rarity.cummon;

import dev.uprank.prison.reward.builder.RewardBuilder;
import dev.uprank.prison.reward.rarity.Rarity;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

public class UnCommonItems {

    private Inventory inventory;

    public UnCommonItems() {
        this.inventory.setItem(0, new RewardBuilder(Material.NETHERITE_SWORD, 1, (byte) 0).setRarity(Rarity.LEGENDARY).addEnchant(Enchantment.getByKey(NamespacedKey.fromString("asdas")), 1).build());
    }
}
