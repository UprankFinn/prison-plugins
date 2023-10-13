package dev.uprank.prison.enchantment.weapon;

import dev.uprank.prison.Prison;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Fireaspect extends Enchantment {

    private final Prison plugin;

    public Fireaspect(Prison plugin) {
        super(new NamespacedKey(plugin, "Xfireaspect"));
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Fireaspect";
    }

    @Override
    public int getMaxLevel() {
        return 100;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
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
