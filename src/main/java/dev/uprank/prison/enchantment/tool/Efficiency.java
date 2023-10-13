package dev.uprank.prison.enchantment.tool;

import dev.uprank.prison.Prison;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Efficiency extends Enchantment {

    private final Prison plugin;

    public Efficiency(Prison plugin) {
        super(new NamespacedKey(plugin, "Xefficiency"));
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Efficiency";
    }

    @Override
    public int getMaxLevel() {
        return 1000;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }
}
