package dev.uprank.prison.enchantment.tool;

import dev.uprank.prison.Prison;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Unbreaking extends Enchantment {

    private final Prison plugin;

    public Unbreaking(Prison plugin) {
        super(new NamespacedKey(plugin, "Xunbreaking"));
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Unbreaking";
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
